package scala.collection.mutable;

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
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.LinearSeqOps;
import scala.collection.SeqFactory;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.StrictOptimizedSeqOps;
import scala.collection.View;
import scala.collection.generic.CommonErrors$;
import scala.collection.generic.DefaultSerializable;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.math.Integral;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0005h\u0001B\u001b7\u0001uBQA\u001b\u0001\u0005\u0002-Da\u0001\u001c\u0001!B\u0013i\u0007b\u0002;\u0001\u0001\u0004%I!\u001e\u0005\bm\u0002\u0001\r\u0011\"\u0003x\u0011\u0019i\b\u0001)Q\u00057\"9a\u0010\u0001a\u0001\n\u0013y\b\"CA\u0004\u0001\u0001\u0007I\u0011BA\u0005\u0011!\ti\u0001\u0001Q!\n\u0005\u0005\u0001\u0002CA\b\u0001\u0001\u0006K!!\u0005\t\u000f\u0005]\u0001\u0001)Q\u0005[\u00161\u0011\u0011\u0004\u0001\u0005\u00037Aq!!\n\u0001\t\u0003\t9\u0003C\u0004\u00020\u0001!\t%!\r\t\u000f\u0005e\u0002\u0001\"\u0001\u0002<!9\u0011q\u0014\u0001\u0005\u0002\u0005\u0005\u0006bBAR\u0001\u0011\u0005\u0013\u0011\u0015\u0005\b\u0003K\u0003A\u0011IAT\u0011\u001d\tI\u000b\u0001C\u0005\u0003WCq!!,\u0001\t\u0013\tY\u000b\u0003\u0004\u00020\u0002!\t%\u001e\u0005\b\u0003c\u0003A\u0011AAZ\u0011\u001d\t)\f\u0001C\u0001\u0003oCq!!0\u0001\t\u0003\tY\u000bC\u0004\u0002@\u0002!)!!1\t\u000f\u0005%\u0007\u0001\"\u0003\u0002L\"9\u0011Q\u001b\u0001\u0005F\u0005]\u0007bBAn\u0001\u0011\u0005\u0013Q\u001c\u0005\b\u0003C\u0004A\u0011BAr\u0011\u001d\tI\u000f\u0001C\u0005\u0003WDq!!=\u0001\t\u0013\t\u0019\u0010C\u0004\u0002z\u0002!\t!a?\t\u000f\t\r\u0001\u0001\"\u0001\u0003\u0006!9!1\u0002\u0001\u0005\u0002\t5\u0001b\u0002B\t\u0001\u0011%!1\u0003\u0005\b\u0005;\u0001A\u0011\u0001B\u0010\u0011\u001d\u00119\u0003\u0001C\u0001\u0005SAqAa\n\u0001\t\u0003\u0011i\u0003C\u0004\u00036\u0001!IAa\u000e\t\u000f\t}\u0002\u0001\"\u0001\u0003B!9!Q\n\u0001\u0005\u0002\t=\u0003b\u0002B+\u0001\u0011\u0005!q\u000b\u0005\b\u0005;\u0002A\u0011\u0001B0\u0011\u001d\u0011i\u0007\u0001C!\u0005_BqA!\u001d\u0001\t\u0003\u0012\u0019\b\u0003\u0005\u0003|\u0001\u0001K\u0011\u000bB?\u000f\u001d\u0011yI\u000eE\u0001\u0005#3a!\u000e\u001c\t\u0002\tM\u0005B\u000260\t\u0003\u0011\t\u000bC\u0004\u0003d=\"\tAa)\t\u000f\tMv\u0006\"\u0001\u00036\"9!QY\u0018\u0005\u0002\t\u001d\u0007\"\u0003Bi_\u0005\u0005I\u0011\u0002Bj\u0005)a\u0015n\u001d;Ck\u001a4WM\u001d\u0006\u0003oa\nq!\\;uC\ndWM\u0003\u0002:u\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003m\nQa]2bY\u0006\u001c\u0001!\u0006\u0002?\u000bN9\u0001aP(U1\u0006$\u0007c\u0001!B\u00076\ta'\u0003\u0002Cm\tq\u0011IY:ue\u0006\u001cGOQ;gM\u0016\u0014\bC\u0001#F\u0019\u0001!QA\u0012\u0001C\u0002\u001d\u0013\u0011!Q\t\u0003\u00112\u0003\"!\u0013&\u000e\u0003iJ!a\u0013\u001e\u0003\u000f9{G\u000f[5oOB\u0011\u0011*T\u0005\u0003\u001dj\u00121!\u00118z!\u0015\u0001\u0005k\u0011*T\u0013\t\tfG\u0001\u0004TKF|\u0005o\u001d\t\u0003\u0001\u0002\u00012\u0001\u0011\u0001D!\u0015)fk\u0011*T\u001b\u0005A\u0014BA,9\u0005U\u0019FO]5di>\u0003H/[7ju\u0016$7+Z9PaN\u0004B\u0001Q-D7&\u0011!L\u000e\u0002\u0010%\u0016,8/\u00192mK\n+\u0018\u000e\u001c3feB\u0019AlX\"\u000e\u0003uS!A\u0018\u001d\u0002\u0013%lW.\u001e;bE2,\u0017B\u00011^\u0005\u0011a\u0015n\u001d;\u0011\tU\u00137IU\u0005\u0003Gb\u0012q#\u0013;fe\u0006\u0014G.\u001a$bGR|'/\u001f#fM\u0006,H\u000e^:\u0011\u0005\u0015DW\"\u00014\u000b\u0005\u001dD\u0014aB4f]\u0016\u0014\u0018nY\u0005\u0003S\u001a\u00141\u0003R3gCVdGoU3sS\u0006d\u0017N_1cY\u0016\fa\u0001P5oSRtD#A*\u0002\u001b5,H/\u0019;j_:\u001cu.\u001e8u!\tIe.\u0003\u0002pu\t\u0019\u0011J\u001c;)\u0005\t\t\bCA%s\u0013\t\u0019(HA\u0005ue\u0006t7/[3oi\u0006)a-\u001b:tiV\t1,A\u0005gSJ\u001cHo\u0018\u0013fcR\u0011\u0001p\u001f\t\u0003\u0013fL!A\u001f\u001e\u0003\tUs\u0017\u000e\u001e\u0005\by\u0012\t\t\u00111\u0001\\\u0003\rAH%M\u0001\u0007M&\u00148\u000f\u001e\u0011\u0002\u000b1\f7\u000f\u001e\u0019\u0016\u0005\u0005\u0005\u0001\u0003\u0002/\u0002\u0004\rK1!!\u0002^\u00051!3m\u001c7p]\u0012\u001aw\u000e\\8o\u0003%a\u0017m\u001d;1?\u0012*\u0017\u000fF\u0002y\u0003\u0017A\u0001\u0002`\u0004\u0002\u0002\u0003\u0007\u0011\u0011A\u0001\u0007Y\u0006\u001cH\u000f\r\u0011\u0002\u000f\u0005d\u0017.Y:fIB\u0019\u0011*a\u0005\n\u0007\u0005U!HA\u0004C_>dW-\u00198\u0002\u00071,gNA\u0006Qe\u0016$WmY3tg>\u0014X\u0003BA\u000f\u0003C\u0001R\u0001XA\u0002\u0003?\u00012\u0001RA\u0011\t\u0019\t\u0019c\u0003b\u0001\u000f\n\u0011\u0011\tM\u0001\tSR,'/\u0019;peV\u0011\u0011\u0011\u0006\t\u0005+\u0006-2)C\u0002\u0002.a\u0012\u0001\"\u0013;fe\u0006$xN]\u0001\u0010SR,'/\u00192mK\u001a\u000b7\r^8ssV\u0011\u00111\u0007\t\u0005+\u0006U\"+C\u0002\u00028a\u0012!bU3r\r\u0006\u001cGo\u001c:z\u0003\u0015\t\u0007\u000f\u001d7z)\r\u0019\u0015Q\b\u0005\u0007\u0003\u007fq\u0001\u0019A7\u0002\u0003%DSADA\"\u00033\u0002R!SA#\u0003\u0013J1!a\u0012;\u0005\u0019!\bN]8xgB!\u00111JA+\u001b\t\tiE\u0003\u0003\u0002P\u0005E\u0013\u0001\u00027b]\u001eT!!a\u0015\u0002\t)\fg/Y\u0005\u0005\u0003/\niEA\rJ]\u0012,\u0007pT;u\u001f\u001a\u0014u.\u001e8eg\u0016C8-\u001a9uS>t\u0017g\u0002\u0010\u0002\\\u0005E\u0014Q\u0014\t\u0005\u0003;\nYG\u0004\u0003\u0002`\u0005\u001d\u0004cAA1u5\u0011\u00111\r\u0006\u0004\u0003Kb\u0014A\u0002\u001fs_>$h(C\u0002\u0002ji\na\u0001\u0015:fI\u00164\u0017\u0002BA7\u0003_\u0012aa\u0015;sS:<'bAA5uEJ1%a\u001d\u0002|\u0005M\u0015QP\u000b\u0005\u0003k\n9(\u0006\u0002\u0002\\\u00119\u0011\u0011\u0010\u001fC\u0002\u0005\r%!\u0001+\n\t\u0005u\u0014qP\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u0019\u000b\u0007\u0005\u0005%(\u0001\u0004uQJ|wo]\t\u0004\u0011\u0006\u0015\u0005\u0003BAD\u0003\u001bs1!SAE\u0013\r\tYIO\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\ty)!%\u0003\u0013QC'o\\<bE2,'bAAFuEJ1%!&\u0002\u0018\u0006e\u0015\u0011\u0011\b\u0004\u0013\u0006]\u0015bAAAuE*!%\u0013\u001e\u0002\u001c\n)1oY1mCF\u001aa%!\u0013\u0002\r1,gn\u001a;i+\u0005i\u0017!C6o_^t7+\u001b>f\u0003\u001dI7/R7qif,\"!!\u0005\u0002\u0013\r|\u0007/_#mK6\u001cH#\u0001=\u0002\u001f\u0015t7/\u001e:f+:\fG.[1tK\u0012\fa\u0001^8MSN$\u0018A\u0002:fgVdG\u000fF\u0001\\\u00035\u0001(/\u001a9f]\u0012$v\u000eT5tiR\u00191,!/\t\r\u0005mf\u00031\u0001\\\u0003\tA8/A\u0003dY\u0016\f'/\u0001\u0004bI\u0012|e.\u001a\u000b\u0005\u0003\u0007\f)-D\u0001\u0001\u0011\u0019\t9\r\u0007a\u0001\u0007\u0006!Q\r\\3n\u0003%1'/Z:i\rJ|W\u000e\u0006\u0003\u0002D\u00065\u0007bBA^3\u0001\u0007\u0011q\u001a\t\u0005+\u0006E7)C\u0002\u0002Tb\u0012A\"\u0013;fe\u0006\u0014G.Z(oG\u0016\fa!\u00193e\u00032dG\u0003BAb\u00033Dq!a/\u001b\u0001\u0004\ty-A\u0006tk\n$(/Y2u\u001f:,G\u0003BAb\u0003?Da!a2\u001c\u0001\u0004\u0019\u0015A\u0004:fIV\u001cW\rT3oORD')\u001f\u000b\u0004q\u0006\u0015\bBBAt9\u0001\u0007Q.A\u0002ok6\fa\u0001\\8dCR,G\u0003BAw\u0003_\u0004B!a1\f\u0007\"1\u0011qH\u000fA\u00025\fqaZ3u\u001d\u0016DH\u000fF\u0002\\\u0003kDq!a>\u001f\u0001\u0004\ti/A\u0001q\u0003\u0019)\b\u000fZ1uKR)\u00010!@\u0003\u0002!1\u0011q`\u0010A\u00025\f1!\u001b3y\u0011\u0019\t9m\ba\u0001\u0007\u00061\u0011N\\:feR$R\u0001\u001fB\u0004\u0005\u0013Aa!a@!\u0001\u0004i\u0007BBAdA\u0001\u00071)A\u0004qe\u0016\u0004XM\u001c3\u0015\t\u0005\r'q\u0002\u0005\u0007\u0003\u000f\f\u0003\u0019A\"\u0002\u0017%t7/\u001a:u\u0003\u001a$XM\u001d\u000b\u0006q\nU!\u0011\u0004\u0005\b\u0005/\u0011\u0003\u0019AAw\u0003\u0011\u0001(/\u001a<\t\r\tm!\u00051\u0001T\u0003\u00151'/Z:i\u0003%Ign]3si\u0006cG\u000eF\u0003y\u0005C\u0011\u0019\u0003\u0003\u0004\u0002\u0000\u000e\u0002\r!\u001c\u0005\b\u0005K\u0019\u0003\u0019AAh\u0003\u0015)G.Z7t\u0003\u0019\u0011X-\\8wKR\u00191Ia\u000b\t\r\u0005}H\u00051\u0001n)\u0015A(q\u0006B\u0019\u0011\u0019\ty0\na\u0001[\"1!1G\u0013A\u00025\fQaY8v]R\f1B]3n_Z,\u0017I\u001a;feR)\u0001P!\u000f\u0003<!9!q\u0003\u0014A\u0002\u00055\bB\u0002B\u001fM\u0001\u0007Q.A\u0001o\u0003)i\u0017\r]%o!2\f7-\u001a\u000b\u0005\u0003\u0007\u0014\u0019\u0005C\u0004\u0003F\u001d\u0002\rAa\u0012\u0002\u0003\u0019\u0004R!\u0013B%\u0007\u000eK1Aa\u0013;\u0005%1UO\\2uS>t\u0017'\u0001\bgY\u0006$X*\u00199J]Bc\u0017mY3\u0015\t\u0005\r'\u0011\u000b\u0005\b\u0005\u000bB\u0003\u0019\u0001B*!\u0019I%\u0011J\"\u0002P\u0006ia-\u001b7uKJLe\u000e\u00157bG\u0016$B!a1\u0003Z!9\u0011q_\u0015A\u0002\tm\u0003CB%\u0003J\r\u000b\t\"\u0001\u0007qCR\u001c\u0007.\u00138QY\u0006\u001cW\r\u0006\u0005\u0002D\n\u0005$Q\rB5\u0011\u0019\u0011\u0019G\u000ba\u0001[\u0006!aM]8n\u0011\u001d\u00119G\u000ba\u0001\u0003\u001f\fQ\u0001]1uG\"DaAa\u001b+\u0001\u0004i\u0017\u0001\u0003:fa2\f7-\u001a3\u0002\t1\f7\u000f^\u000b\u0002\u0007\u0006QA.Y:u\u001fB$\u0018n\u001c8\u0016\u0005\tU\u0004\u0003B%\u0003x\rK1A!\u001f;\u0005\u0019y\u0005\u000f^5p]\u0006a1\u000f\u001e:j]\u001e\u0004&/\u001a4jqV\u0011!q\u0010\t\u0005\u0003\u0017\u0012\t)\u0003\u0003\u0002n\u00055\u0003f\u0002\u0001\u0003\u0006\n-%Q\u0012\t\u0004\u0013\n\u001d\u0015b\u0001BEu\t\u00012+\u001a:jC24VM]:j_:,\u0016\nR\u0001\u0006m\u0006dW/\u001a\u0010\t\u0017#\t|d3|;\u0010\u0006QA*[:u\u0005V4g-\u001a:\u0011\u0005\u0001{3#B\u0018\u0003\u0016\nm\u0005cA%\u0003\u0018&\u0019!\u0011\u0014\u001e\u0003\r\u0005s\u0017PU3g!\u0011)&Q\u0014*\n\u0007\t}\u0005HA\rTiJL7\r^(qi&l\u0017N_3e'\u0016\fh)Y2u_JLHC\u0001BI+\u0011\u0011)Ka+\u0015\t\t\u001d&Q\u0016\t\u0005\u0001\u0002\u0011I\u000bE\u0002E\u0005W#QAR\u0019C\u0002\u001dCqAa,2\u0001\u0004\u0011\t,\u0001\u0003d_2d\u0007#B+\u0002R\n%\u0016A\u00038fo\n+\u0018\u000e\u001c3feV!!q\u0017Ba+\t\u0011I\fE\u0004A\u0005w\u0013yLa1\n\u0007\tufGA\u0004Ck&dG-\u001a:\u0011\u0007\u0011\u0013\t\rB\u0003Ge\t\u0007q\t\u0005\u0003A\u0001\t}\u0016!B3naRLX\u0003\u0002Be\u0005\u001f,\"Aa3\u0011\t\u0001\u0003!Q\u001a\t\u0004\t\n=G!\u0002$4\u0005\u00049\u0015\u0001D<sSR,'+\u001a9mC\u000e,GC\u0001Bk!\u0011\tYEa6\n\t\te\u0017Q\n\u0002\u0007\u001f\nTWm\u0019;)\u000f=\u0012)Ia#\u0003^z\t1\u0001K\u0004/\u0005\u000b\u0013YI!8"
)
public class ListBuffer extends AbstractBuffer implements StrictOptimizedSeqOps, ReusableBuilder, DefaultSerializable {
   private static final long serialVersionUID = -8428291952499836345L;
   private transient int mutationCount = 0;
   private List first;
   private $colon$colon last0;
   private boolean aliased;
   private int len;

   public static Builder newBuilder() {
      return ListBuffer$.MODULE$.newBuilder();
   }

   public static ListBuffer from(final IterableOnce coll) {
      return ListBuffer$.MODULE$.from(coll);
   }

   public static scala.collection.SeqOps tabulate(final int n, final Function1 f) {
      Builder tabulate_b = ListBuffer$.MODULE$.newBuilder();
      tabulate_b.sizeHint(n);

      for(int tabulate_i = 0; tabulate_i < n; ++tabulate_i) {
         Object tabulate_$plus$eq_elem = f.apply(tabulate_i);
         tabulate_b.addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)tabulate_b.result();
   }

   public static scala.collection.SeqOps fill(final int n, final Function0 elem) {
      Builder fill_b = ListBuffer$.MODULE$.newBuilder();
      fill_b.sizeHint(n);

      for(int fill_i = 0; fill_i < n; ++fill_i) {
         Object fill_$plus$eq_elem = elem.apply();
         fill_b.addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)fill_b.result();
   }

   public static scala.collection.SeqOps unapplySeq(final scala.collection.SeqOps x) {
      ListBuffer$ var10000 = ListBuffer$.MODULE$;
      return x;
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f) {
      ListBuffer$ tabulate_this = ListBuffer$.MODULE$;
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
      ListBuffer$ tabulate_this = ListBuffer$.MODULE$;
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
      ListBuffer$ tabulate_this = ListBuffer$.MODULE$;
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
      ListBuffer$ tabulate_this = ListBuffer$.MODULE$;
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
      ListBuffer$ fill_this = ListBuffer$.MODULE$;
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
      ListBuffer$ fill_this = ListBuffer$.MODULE$;
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
      ListBuffer$ fill_this = ListBuffer$.MODULE$;
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
      ListBuffer$ fill_this = ListBuffer$.MODULE$;
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
      return IterableFactory.range$(ListBuffer$.MODULE$, start, end, step, evidence$4);
   }

   public static Object range(final Object start, final Object end, final Integral evidence$3) {
      return IterableFactory.range$(ListBuffer$.MODULE$, start, end, evidence$3);
   }

   public static Object unfold(final Object init, final Function1 f) {
      ListBuffer$ unfold_this = ListBuffer$.MODULE$;
      IterableOnce from_source = new View.Unfold(init, f);
      return unfold_this.from(from_source);
   }

   public static Object iterate(final Object start, final int len, final Function1 f) {
      ListBuffer$ iterate_this = ListBuffer$.MODULE$;
      IterableOnce from_source = new View.Iterate(start, len, f);
      return iterate_this.from(from_source);
   }

   public Object writeReplace() {
      return DefaultSerializable.writeReplace$(this);
   }

   public void sizeHint(final int size) {
      Builder.sizeHint$(this, size);
   }

   public final void sizeHint(final IterableOnce coll, final int delta) {
      Builder.sizeHint$(this, coll, delta);
   }

   public final int sizeHint$default$2() {
      return Builder.sizeHint$default$2$(this);
   }

   public final void sizeHintBounded(final int size, final scala.collection.Iterable boundingColl) {
      Builder.sizeHintBounded$(this, size, boundingColl);
   }

   public Builder mapResult(final Function1 f) {
      return Builder.mapResult$(this, f);
   }

   public Object distinctBy(final Function1 f) {
      return StrictOptimizedSeqOps.distinctBy$(this, f);
   }

   public Object prepended(final Object elem) {
      return StrictOptimizedSeqOps.prepended$(this, elem);
   }

   public Object appended(final Object elem) {
      return StrictOptimizedSeqOps.appended$(this, elem);
   }

   public Object appendedAll(final IterableOnce suffix) {
      return StrictOptimizedSeqOps.appendedAll$(this, suffix);
   }

   public Object prependedAll(final IterableOnce prefix) {
      return StrictOptimizedSeqOps.prependedAll$(this, prefix);
   }

   public Object padTo(final int len, final Object elem) {
      return StrictOptimizedSeqOps.padTo$(this, len, elem);
   }

   public Object diff(final scala.collection.Seq that) {
      return StrictOptimizedSeqOps.diff$(this, that);
   }

   public Object intersect(final scala.collection.Seq that) {
      return StrictOptimizedSeqOps.intersect$(this, that);
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

   private List first() {
      return this.first;
   }

   private void first_$eq(final List x$1) {
      this.first = x$1;
   }

   private $colon$colon last0() {
      return this.last0;
   }

   private void last0_$eq(final $colon$colon x$1) {
      this.last0 = x$1;
   }

   public Iterator iterator() {
      return new MutationTracker.CheckedIterator(this.first().iterator(), () -> this.mutationCount);
   }

   public SeqFactory iterableFactory() {
      return ListBuffer$.MODULE$;
   }

   public Object apply(final int i) throws IndexOutOfBoundsException {
      List var10000 = this.first();
      if (var10000 == null) {
         throw null;
      } else {
         return LinearSeqOps.apply$(var10000, i);
      }
   }

   public int length() {
      return this.len;
   }

   public int knownSize() {
      return this.len;
   }

   public boolean isEmpty() {
      return this.len == 0;
   }

   private void copyElems() {
      ListBuffer buf = (new ListBuffer()).scala$collection$mutable$ListBuffer$$freshFrom(this);
      this.first_$eq(buf.first());
      this.last0_$eq(buf.last0());
      this.aliased = false;
   }

   private void ensureUnaliased() {
      ++this.mutationCount;
      if (this.aliased) {
         this.copyElems();
      }
   }

   public List toList() {
      this.aliased = this.nonEmpty();
      Statics.releaseFence();
      return this.first();
   }

   public List result() {
      return this.toList();
   }

   public List prependToList(final List xs) {
      if (this.isEmpty()) {
         return xs;
      } else {
         this.ensureUnaliased();
         this.last0().next_$eq(xs);
         return this.toList();
      }
   }

   public void clear() {
      ++this.mutationCount;
      this.first_$eq(Nil$.MODULE$);
      this.len = 0;
      this.last0_$eq(($colon$colon)null);
      this.aliased = false;
   }

   public final ListBuffer addOne(final Object elem) {
      this.ensureUnaliased();
      $colon$colon last1 = new $colon$colon(elem, Nil$.MODULE$);
      if (this.len == 0) {
         this.first_$eq(last1);
      } else {
         this.last0().next_$eq(last1);
      }

      this.last0_$eq(last1);
      ++this.len;
      return this;
   }

   public ListBuffer scala$collection$mutable$ListBuffer$$freshFrom(final IterableOnce xs) {
      Iterator it = xs.iterator();
      if (it.hasNext()) {
         int len = 1;
         $colon$colon last0 = new $colon$colon(it.next(), Nil$.MODULE$);
         this.first_$eq(last0);

         while(it.hasNext()) {
            $colon$colon last1 = new $colon$colon(it.next(), Nil$.MODULE$);
            last0.next_$eq(last1);
            last0 = last1;
            ++len;
         }

         this.len = len;
         this.last0_$eq(last0);
      }

      return this;
   }

   public final ListBuffer addAll(final IterableOnce xs) {
      Iterator it = xs.iterator();
      if (it.hasNext()) {
         ListBuffer fresh = (new ListBuffer()).scala$collection$mutable$ListBuffer$$freshFrom(it);
         this.ensureUnaliased();
         if (this.len == 0) {
            this.first_$eq(fresh.first());
         } else {
            this.last0().next_$eq(fresh.first());
         }

         this.last0_$eq(fresh.last0());
         this.len += fresh.length();
      }

      return this;
   }

   public ListBuffer subtractOne(final Object elem) {
      this.ensureUnaliased();
      if (!this.isEmpty()) {
         if (BoxesRunTime.equals(this.first().head(), elem)) {
            this.first_$eq((List)this.first().tail());
            this.reduceLengthBy(1);
         } else {
            List cursor;
            for(cursor = this.first(); !((List)cursor.tail()).isEmpty() && !BoxesRunTime.equals(((IterableOps)cursor.tail()).head(), elem); cursor = (List)cursor.tail()) {
            }

            if (!((List)cursor.tail()).isEmpty()) {
               $colon$colon z;
               label23: {
                  z = ($colon$colon)cursor;
                  List var10000 = z.next();
                  $colon$colon var4 = this.last0();
                  if (var10000 == null) {
                     if (var4 != null) {
                        break label23;
                     }
                  } else if (!var10000.equals(var4)) {
                     break label23;
                  }

                  this.last0_$eq(z);
               }

               z.next_$eq((List)((IterableOps)cursor.tail()).tail());
               this.reduceLengthBy(1);
            }
         }
      }

      return this;
   }

   private void reduceLengthBy(final int num) {
      this.len -= num;
      if (this.len <= 0) {
         this.last0_$eq(($colon$colon)null);
      }
   }

   private $colon$colon locate(final int i) {
      if (i == 0) {
         return null;
      } else if (i == this.len) {
         return this.last0();
      } else {
         int j = i - 1;

         List p;
         for(p = this.first(); j > 0; --j) {
            p = (List)p.tail();
         }

         return ($colon$colon)p;
      }
   }

   private List getNext(final $colon$colon p) {
      return p == null ? this.first() : p.next();
   }

   public void update(final int idx, final Object elem) {
      this.ensureUnaliased();
      if (idx >= 0 && idx < this.len) {
         if (idx == 0) {
            $colon$colon newElem = new $colon$colon(elem, (List)this.first().tail());
            if (this.last0() == this.first()) {
               this.last0_$eq(newElem);
            }

            this.first_$eq(newElem);
         } else {
            $colon$colon p = this.locate(idx);
            $colon$colon var10000 = new $colon$colon;
            if (p == null) {
               throw null;
            } else {
               var10000.<init>(elem, (List)p.next().tail());
               $colon$colon newElem = var10000;
               if (this.last0() == p.next()) {
                  this.last0_$eq(newElem);
               }

               p.next_$eq(newElem);
            }
         }
      } else {
         throw CommonErrors$.MODULE$.indexOutOfBounds(idx, this.len - 1);
      }
   }

   public void insert(final int idx, final Object elem) {
      this.ensureUnaliased();
      if (idx >= 0 && idx <= this.len) {
         if (idx == this.len) {
            this.addOne(elem);
         } else {
            $colon$colon p = this.locate(idx);
            List var10000 = this.getNext(p);
            if (var10000 == null) {
               throw null;
            } else {
               List $colon$colon_this = var10000;
               $colon$colon var7 = new $colon$colon(elem, $colon$colon_this);
               $colon$colon_this = null;
               List nx = var7;
               if (p == null) {
                  this.first_$eq(nx);
               } else {
                  p.next_$eq(nx);
               }

               ++this.len;
            }
         }
      } else {
         throw CommonErrors$.MODULE$.indexOutOfBounds(idx, this.len - 1);
      }
   }

   public ListBuffer prepend(final Object elem) {
      this.insert(0, elem);
      return this;
   }

   private void insertAfter(final $colon$colon prev, final ListBuffer fresh) {
      if (!fresh.isEmpty()) {
         List follow = this.getNext(prev);
         if (prev == null) {
            this.first_$eq(fresh.first());
         } else {
            prev.next_$eq(fresh.first());
         }

         fresh.last0().next_$eq(follow);
         if (follow.isEmpty()) {
            this.last0_$eq(fresh.last0());
         }

         this.len += fresh.length();
      }
   }

   public void insertAll(final int idx, final IterableOnce elems) {
      if (idx >= 0 && idx <= this.len) {
         Iterator it = elems.iterator();
         if (it.hasNext()) {
            if (idx == this.len) {
               this.addAll(it);
            } else {
               ListBuffer fresh = (new ListBuffer()).scala$collection$mutable$ListBuffer$$freshFrom(it);
               this.ensureUnaliased();
               this.insertAfter(this.locate(idx), fresh);
            }
         }
      } else {
         throw CommonErrors$.MODULE$.indexOutOfBounds(idx, this.len - 1);
      }
   }

   public Object remove(final int idx) {
      this.ensureUnaliased();
      if (idx >= 0 && idx < this.len) {
         $colon$colon p = this.locate(idx);
         List nx = this.getNext(p);
         if (p == null) {
            this.first_$eq((List)nx.tail());
            if (this.first().isEmpty()) {
               this.last0_$eq(($colon$colon)null);
            }
         } else {
            if (this.last0() == nx) {
               this.last0_$eq(p);
            }

            p.next_$eq((List)nx.tail());
         }

         --this.len;
         return nx.head();
      } else {
         throw CommonErrors$.MODULE$.indexOutOfBounds(idx, this.len - 1);
      }
   }

   public void remove(final int idx, final int count) {
      if (count > 0) {
         this.ensureUnaliased();
         if (idx >= 0 && idx + count <= this.len) {
            this.removeAfter(this.locate(idx), count);
         } else {
            throw new IndexOutOfBoundsException((new java.lang.StringBuilder(35)).append(idx).append(" to ").append(idx + count).append(" is out of bounds (min 0, max ").append(this.len - 1).append(")").toString());
         }
      } else if (count < 0) {
         throw new IllegalArgumentException((new java.lang.StringBuilder(38)).append("removing negative number of elements: ").append(count).toString());
      }
   }

   private void removeAfter(final $colon$colon prev, final int n) {
      List nx = this.ahead$1(this.getNext(prev), n);
      if (prev == null) {
         this.first_$eq(nx);
      } else {
         prev.next_$eq(nx);
      }

      if (nx.isEmpty()) {
         this.last0_$eq(prev);
      }

      this.len -= n;
   }

   public ListBuffer mapInPlace(final Function1 f) {
      ++this.mutationCount;
      ListBuffer buf = new ListBuffer();
      this.foreach((elem) -> {
         Object $plus$eq_elem = f.apply(elem);
         return (ListBuffer)buf.addOne($plus$eq_elem);
      });
      this.first_$eq(buf.first());
      this.last0_$eq(buf.last0());
      this.aliased = false;
      return this;
   }

   public ListBuffer flatMapInPlace(final Function1 f) {
      ++this.mutationCount;
      List src = this.first();
      List dst = null;
      this.last0_$eq(($colon$colon)null);

      for(this.len = 0; !src.isEmpty(); src = (List)src.tail()) {
         for(Iterator it = ((IterableOnce)f.apply(src.head())).iterator(); it.hasNext(); ++this.len) {
            $colon$colon v = new $colon$colon(it.next(), Nil$.MODULE$);
            if (dst == null) {
               dst = v;
            } else {
               this.last0().next_$eq(v);
            }

            this.last0_$eq(v);
         }
      }

      this.first_$eq((List)(dst == null ? Nil$.MODULE$ : dst));
      this.aliased = false;
      return this;
   }

   public ListBuffer filterInPlace(final Function1 p) {
      this.ensureUnaliased();
      $colon$colon prev = null;

      List follow;
      for(List cur = this.first(); !cur.isEmpty(); cur = follow) {
         follow = (List)cur.tail();
         if (!BoxesRunTime.unboxToBoolean(p.apply(cur.head()))) {
            if (prev == null) {
               this.first_$eq(follow);
            } else {
               prev.next_$eq(follow);
            }

            --this.len;
         } else {
            prev = ($colon$colon)cur;
         }
      }

      this.last0_$eq(prev);
      return this;
   }

   public ListBuffer patchInPlace(final int from, final IterableOnce patch, final int replaced) {
      int _len = this.len;
      scala.math.package$ var10000 = scala.math.package$.MODULE$;
      int max_y = 0;
      int _from = Math.max(from, max_y);
      var10000 = scala.math.package$.MODULE$;
      int max_y = 0;
      int _replaced = Math.max(replaced, max_y);
      Iterator it = patch.iterator();
      boolean nonEmptyPatch = it.hasNext();
      boolean nonEmptyReplace = _from < _len && _replaced > 0;
      if (nonEmptyPatch || nonEmptyReplace) {
         ListBuffer fresh = (new ListBuffer()).scala$collection$mutable$ListBuffer$$freshFrom(it);
         this.ensureUnaliased();
         var10000 = scala.math.package$.MODULE$;
         int i = Math.min(_from, _len);
         var10000 = scala.math.package$.MODULE$;
         int n = Math.min(_replaced, _len);
         $colon$colon p = this.locate(i);
         scala.math.package$ var10002 = scala.math.package$.MODULE$;
         int min_y = _len - i;
         this.removeAfter(p, Math.min(n, min_y));
         this.insertAfter(p, fresh);
      }

      return this;
   }

   public Object last() {
      if (this.last0() == null) {
         throw new NoSuchElementException("last of empty ListBuffer");
      } else {
         return this.last0().head();
      }
   }

   public Option lastOption() {
      return (Option)(this.last0() == null ? None$.MODULE$ : new Some(this.last0().head()));
   }

   public String stringPrefix() {
      return "ListBuffer";
   }

   private final List ahead$1(final List p, final int n) {
      while(n != 0) {
         List var10000 = (List)p.tail();
         --n;
         p = var10000;
      }

      return p;
   }

   public ListBuffer() {
      this.first = Nil$.MODULE$;
      this.last0 = null;
      this.aliased = false;
      this.len = 0;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
