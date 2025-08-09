package scala.util;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.reflect.ScalaSignature;
import scala.util.control.NonFatal$;

@ScalaSignature(
   bytes = "\u0006\u0005\t=u!\u0002\u0013&\u0011\u0003Qc!\u0002\u0017&\u0011\u0003i\u0003\"\u0002\u001a\u0002\t\u0003\u0019\u0004\"\u0002\u001b\u0002\t\u0003)d\u0001B2\u0002\u0005\u0011DQA\r\u0003\u0005\n\u0015Dqa\u001a\u0003A\u0002\u0013%\u0001\u000eC\u0004m\t\u0001\u0007I\u0011B7\t\rA$\u0001\u0015)\u0003j\u0011\u0019\tH\u0001)Q\u0005e\"1A\u0007\u0002C\u0001\u0003{Aq!!\u0015\u0005\t\u0003\t\u0019\u0006C\u0004\u0002f\u0011!I!a\u001a\b\u000f\u0005\r\u0011\u0001#\u0001\u0002\u0006\u001911-\u0001E\u0001\u0003\u000fAaA\r\b\u0005\u0002\u0005%\u0001B\u0002\u001b\u000f\t\u0003\tYA\u0002\u0004\u0002\u001c91\u0011Q\u0004\u0005\n)F\u0011\t\u0011)A\u0005\u0003CA!\"!\n\u0012\u0005\u0003\u0005\u000b1BA\u0014\u0011\u0019\u0011\u0014\u0003\"\u0001\u0002*!1a*\u0005C\u0001\u0003kAq!a\u001d\u0002\t\u0013\t)\b\u0003\u0004U\u0003\u0011\u0005\u0011Q\u0011\u0005\u0007c\u0006!\t!!)\t\rE\fA\u0011AAl\u0011\u0019\t\u0018\u0001\"\u0001\u0003\u0018\u001991*\u0001I\u0001$\u0003a\u0005\"\u0002(\u001c\r\u0003yua\u0002B3\u0003!\u0005!q\r\u0004\u0007\u0017\u0006A\tA!\u001b\t\rIrB\u0011\u0001B6\u000f\u001d\u0011iG\bE\u0002\u0005_2qAa\u001d\u001f\u0011\u0003\u0011)\b\u0003\u00043C\u0011\u0005!\u0011\u0012\u0005\u0007\u001d\u0006\"\tAa#\u0002\u000bU\u001b\u0018N\\4\u000b\u0005\u0019:\u0013\u0001B;uS2T\u0011\u0001K\u0001\u0006g\u000e\fG.Y\u0002\u0001!\tY\u0013!D\u0001&\u0005\u0015)6/\u001b8h'\t\ta\u0006\u0005\u00020a5\tq%\u0003\u00022O\t1\u0011I\\=SK\u001a\fa\u0001P5oSRtD#\u0001\u0016\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\u0007YJf\b\u0006\u00028?R\u0011\u0001H\u0017\u000b\u0003s\u001d\u00032a\u000b\u001e=\u0013\tYTEA\u0002Uef\u0004\"!\u0010 \r\u0001\u0011)qh\u0001b\u0001\u0001\n\t\u0011)\u0005\u0002B\tB\u0011qFQ\u0005\u0003\u0007\u001e\u0012qAT8uQ&tw\r\u0005\u00020\u000b&\u0011ai\n\u0002\u0004\u0003:L\bb\u0002%\u0004\u0003\u0003\u0005\u001d!S\u0001\u000bKZLG-\u001a8dK\u0012\n\u0004c\u0001&\u001c16\t\u0011A\u0001\u0006SK2,\u0017m]1cY\u0016,\"!\u0014,\u0014\u0005mq\u0013a\u0002:fY\u0016\f7/\u001a\u000b\u0003!N\u0003\"aL)\n\u0005I;#\u0001B+oSRDQ\u0001\u0016\u000fA\u0002U\u000b\u0001B]3t_V\u00148-\u001a\t\u0003{Y#aaV\u000e\t\u0006\u0004\u0001%!\u0001*\u0011\u0005uJF!B,\u0004\u0005\u0004\u0001\u0005\"B.\u0004\u0001\u0004a\u0016!\u00014\u0011\t=j\u0006\fP\u0005\u0003=\u001e\u0012\u0011BR;oGRLwN\\\u0019\t\rQ\u001bA\u00111\u0001a!\ry\u0013\rW\u0005\u0003E\u001e\u0012\u0001\u0002\u00102z]\u0006lWM\u0010\u0002\b\u001b\u0006t\u0017mZ3s'\t!a\u0006F\u0001g!\tQE!\u0001\u0004dY>\u001cX\rZ\u000b\u0002SB\u0011qF[\u0005\u0003W\u001e\u0012qAQ8pY\u0016\fg.\u0001\u0006dY>\u001cX\rZ0%KF$\"\u0001\u00158\t\u000f=<\u0011\u0011!a\u0001S\u0006\u0019\u0001\u0010J\u0019\u0002\u000f\rdwn]3eA\u0005I!/Z:pkJ\u001cWm\u001d\t\u0004gnthB\u0001;z\u001d\t)\b0D\u0001w\u0015\t9\u0018&\u0001\u0004=e>|GOP\u0005\u0002Q%\u0011!pJ\u0001\ba\u0006\u001c7.Y4f\u0013\taXP\u0001\u0003MSN$(B\u0001>(a\ry\u0018\u0011\b\t\u0006\u0003\u0003\t\u0012q\u0007\b\u0003\u00156\tq!T1oC\u001e,'\u000f\u0005\u0002K\u001dM\u0011aB\f\u000b\u0003\u0003\u000b)B!!\u0004\u0002\u0014Q!\u0011qBA\u000b!\u0011Y#(!\u0005\u0011\u0007u\n\u0019\u0002B\u0003@!\t\u0007\u0001\tC\u0004\u0002\u0018A\u0001\r!!\u0007\u0002\u0005=\u0004\b#B\u0018^M\u0006E!\u0001\u0003*fg>,(oY3\u0016\t\u0005}\u00111E\n\u0003#9\u00022!PA\u0012\t\u00159\u0016C1\u0001A\u0003)\u0011X\r\\3bg\u0006\u0014G.\u001a\t\u0005\u0015n\t\t\u0003\u0006\u0003\u0002,\u0005MB\u0003BA\u0017\u0003c\u0001R!a\f\u0012\u0003Ci\u0011A\u0004\u0005\b\u0003K!\u00029AA\u0014\u0011\u0019!F\u00031\u0001\u0002\"Q\t\u0001\u000bE\u0002>\u0003s!!\"a\u000f\n\u0003\u0003\u0005\tQ!\u0001A\u0005\ryF%M\u000b\u0005\u0003\u007f\tI\u0005\u0006\u0003\u0002B\u0005\u0015C\u0003BA\"\u0003\u0017r1!PA#\u0011\u0019!&\u00021\u0001\u0002HA\u0019Q(!\u0013\u0005\u000b]S!\u0019\u0001!\t\u0013\u00055#\"!AA\u0004\u0005=\u0013AC3wS\u0012,gnY3%eA!!jGA$\u0003\u001d\t7-];je\u0016,B!!\u0016\u0002bQ!\u0011qKA2)\r\u0001\u0016\u0011\f\u0005\n\u00037Z\u0011\u0011!a\u0002\u0003;\n!\"\u001a<jI\u0016t7-\u001a\u00134!\u0011Q5$a\u0018\u0011\u0007u\n\t\u0007B\u0003X\u0017\t\u0007\u0001\t\u0003\u0004U\u0017\u0001\u0007\u0011qL\u0001\u0007[\u0006t\u0017mZ3\u0016\t\u0005%\u0014Q\u000e\u000b\u0005\u0003W\ny\u0007E\u0002>\u0003[\"Qa\u0010\u0007C\u0002\u0001Cq!a\u0006\r\u0001\u0004\t\t\bE\u00030;\u001a\fY'\u0001\fqe\u00164WM]3oi&\fG\u000e\\=TkB\u0004(/Z:t)\u0019\t9(! \u0002\u0002B\u00191/!\u001f\n\u0007\u0005mTPA\u0005UQJ|w/\u00192mK\"9\u0011q\u0010\fA\u0002\u0005]\u0014a\u00029sS6\f'/\u001f\u0005\b\u0003\u00073\u0002\u0019AA<\u0003%\u0019XmY8oI\u0006\u0014\u00180\u0006\u0004\u0002\b\u0006]\u0015q\u0012\u000b\u0005\u0003\u0013\u000by\n\u0006\u0003\u0002\f\u0006eE\u0003BAG\u0003#\u00032!PAH\t\u0015ytC1\u0001A\u0011\u001d\t)c\u0006a\u0002\u0003'\u0003BAS\u000e\u0002\u0016B\u0019Q(a&\u0005\u000b];\"\u0019\u0001!\t\u000f\u0005mu\u00031\u0001\u0002\u001e\u0006!!m\u001c3z!\u0019yS,!&\u0002\u000e\"1Ak\u0006a\u0001\u0003++\u0002\"a)\u00026\u0006\u0005\u00171\u0016\u000b\u0007\u0003K\u000bi-!5\u0015\t\u0005\u001d\u0016Q\u0019\u000b\u0007\u0003S\u000bi+!/\u0011\u0007u\nY\u000bB\u0003@1\t\u0007\u0001\tC\u0005\u00020b\t\t\u0011q\u0001\u00022\u0006QQM^5eK:\u001cW\r\n\u001b\u0011\t)[\u00121\u0017\t\u0004{\u0005UFABA\\1\t\u0007\u0001I\u0001\u0002Sc!I\u00111\u0018\r\u0002\u0002\u0003\u000f\u0011QX\u0001\u000bKZLG-\u001a8dK\u0012*\u0004\u0003\u0002&\u001c\u0003\u007f\u00032!PAa\t\u0019\t\u0019\r\u0007b\u0001\u0001\n\u0011!K\r\u0005\b\u00037C\u0002\u0019AAd!%y\u0013\u0011ZAZ\u0003\u007f\u000bI+C\u0002\u0002L\u001e\u0012\u0011BR;oGRLwN\u001c\u001a\t\u000f\u0005=\u0007\u00041\u0001\u00024\u0006I!/Z:pkJ\u001cW-\r\u0005\t\u0003'DB\u00111\u0001\u0002V\u0006I!/Z:pkJ\u001cWM\r\t\u0005_\u0005\fy,\u0006\u0006\u0002Z\u0006-\u0018Q_A\u0000\u0003C$\u0002\"a7\u0003\f\t5!\u0011\u0003\u000b\u0005\u0003;\u0014\u0019\u0001\u0006\u0005\u0002`\u0006\r\u0018Q^A|!\ri\u0014\u0011\u001d\u0003\u0006\u007fe\u0011\r\u0001\u0011\u0005\n\u0003KL\u0012\u0011!a\u0002\u0003O\f!\"\u001a<jI\u0016t7-\u001a\u00137!\u0011Q5$!;\u0011\u0007u\nY\u000f\u0002\u0004\u00028f\u0011\r\u0001\u0011\u0005\n\u0003_L\u0012\u0011!a\u0002\u0003c\f!\"\u001a<jI\u0016t7-\u001a\u00138!\u0011Q5$a=\u0011\u0007u\n)\u0010\u0002\u0004\u0002Df\u0011\r\u0001\u0011\u0005\n\u0003sL\u0012\u0011!a\u0002\u0003w\f!\"\u001a<jI\u0016t7-\u001a\u00139!\u0011Q5$!@\u0011\u0007u\ny\u0010\u0002\u0004\u0003\u0002e\u0011\r\u0001\u0011\u0002\u0003%NBq!a'\u001a\u0001\u0004\u0011)\u0001E\u00060\u0005\u000f\tI/a=\u0002~\u0006}\u0017b\u0001B\u0005O\tIa)\u001e8di&|gn\r\u0005\b\u0003\u001fL\u0002\u0019AAu\u0011!\t\u0019.\u0007CA\u0002\t=\u0001\u0003B\u0018b\u0003gD\u0001Ba\u0005\u001a\t\u0003\u0007!QC\u0001\ne\u0016\u001cx.\u001e:dKN\u0002BaL1\u0002~Va!\u0011\u0004B\u0016\u0005k\u0011yD!\u0013\u0003\"QQ!1\u0004B+\u0005/\u0012YFa\u0018\u0015\t\tu!Q\n\u000b\u000b\u0005?\u0011\u0019C!\f\u00038\t\u0005\u0003cA\u001f\u0003\"\u0011)qH\u0007b\u0001\u0001\"I!Q\u0005\u000e\u0002\u0002\u0003\u000f!qE\u0001\u000bKZLG-\u001a8dK\u0012J\u0004\u0003\u0002&\u001c\u0005S\u00012!\u0010B\u0016\t\u0019\t9L\u0007b\u0001\u0001\"I!q\u0006\u000e\u0002\u0002\u0003\u000f!\u0011G\u0001\fKZLG-\u001a8dK\u0012\n\u0004\u0007\u0005\u0003K7\tM\u0002cA\u001f\u00036\u00111\u00111\u0019\u000eC\u0002\u0001C\u0011B!\u000f\u001b\u0003\u0003\u0005\u001dAa\u000f\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013'\r\t\u0005\u0015n\u0011i\u0004E\u0002>\u0005\u007f!aA!\u0001\u001b\u0005\u0004\u0001\u0005\"\u0003B\"5\u0005\u0005\t9\u0001B#\u0003-)g/\u001b3f]\u000e,G%\r\u001a\u0011\t)[\"q\t\t\u0004{\t%CA\u0002B&5\t\u0007\u0001I\u0001\u0002Si!9\u00111\u0014\u000eA\u0002\t=\u0003#D\u0018\u0003R\t%\"1\u0007B\u001f\u0005\u000f\u0012y\"C\u0002\u0003T\u001d\u0012\u0011BR;oGRLwN\u001c\u001b\t\u000f\u0005='\u00041\u0001\u0003*!A\u00111\u001b\u000e\u0005\u0002\u0004\u0011I\u0006\u0005\u00030C\nM\u0002\u0002\u0003B\n5\u0011\u0005\rA!\u0018\u0011\t=\n'Q\b\u0005\t\u0005CRB\u00111\u0001\u0003d\u0005I!/Z:pkJ\u001cW\r\u000e\t\u0005_\u0005\u00149%\u0001\u0006SK2,\u0017m]1cY\u0016\u0004\"A\u0013\u0010\u0014\u0005yqCC\u0001B4\u0003e\tU\u000f^8DY>\u001cX-\u00192mK&\u001b(+\u001a7fCN\f'\r\\3\u0011\u0007\tE\u0014%D\u0001\u001f\u0005e\tU\u000f^8DY>\u001cX-\u00192mK&\u001b(+\u001a7fCN\f'\r\\3\u0014\t\u0005r#q\u000f\t\u0005\u0015n\u0011I\b\u0005\u0003\u0003|\t\u0015UB\u0001B?\u0015\u0011\u0011yH!!\u0002\t1\fgn\u001a\u0006\u0003\u0005\u0007\u000bAA[1wC&!!q\u0011B?\u00055\tU\u000f^8DY>\u001cX-\u00192mKR\u0011!q\u000e\u000b\u0004!\n5\u0005B\u0002+$\u0001\u0004\u0011I\b"
)
public final class Using {
   public static Object resources(final Object resource1, final Function0 resource2, final Function0 resource3, final Function0 resource4, final Function4 body, final Releasable evidence$9, final Releasable evidence$10, final Releasable evidence$11, final Releasable evidence$12) {
      return Using$.MODULE$.resources(resource1, resource2, resource3, resource4, body, evidence$9, evidence$10, evidence$11, evidence$12);
   }

   public static Object resources(final Object resource1, final Function0 resource2, final Function0 resource3, final Function3 body, final Releasable evidence$6, final Releasable evidence$7, final Releasable evidence$8) {
      return Using$.MODULE$.resources(resource1, resource2, resource3, body, evidence$6, evidence$7, evidence$8);
   }

   public static Object resources(final Object resource1, final Function0 resource2, final Function2 body, final Releasable evidence$4, final Releasable evidence$5) {
      return Using$.MODULE$.resources(resource1, resource2, body, evidence$4, evidence$5);
   }

   public static Object resource(final Object resource, final Function1 body, final Releasable releasable) {
      return Using$.MODULE$.resource(resource, body, releasable);
   }

   public static Try apply(final Function0 resource, final Function1 f, final Releasable evidence$1) {
      return Using$.MODULE$.apply(resource, f, evidence$1);
   }

   public static final class Manager {
      private boolean closed = false;
      private List resources;

      private boolean closed() {
         return this.closed;
      }

      private void closed_$eq(final boolean x$1) {
         this.closed = x$1;
      }

      public Object apply(final Object resource, final Releasable evidence$2) {
         this.acquire(resource, evidence$2);
         return resource;
      }

      public void acquire(final Object resource, final Releasable evidence$3) {
         if (resource == null) {
            throw new NullPointerException("null resource");
         } else if (this.closed()) {
            throw new IllegalStateException("Manager has already been closed");
         } else {
            Using$Manager$Resource var3 = new Using$Manager$Resource(resource, evidence$3);
            List var10001 = this.resources;
            if (var10001 == null) {
               throw null;
            } else {
               List $colon$colon_this = var10001;
               this.resources = new $colon$colon(var3, $colon$colon_this);
            }
         }
      }

      public Object scala$util$Using$Manager$$manage(final Function1 op) {
         Throwable toThrow = null;
         boolean var12 = false;

         Object var10000;
         try {
            var12 = true;
            var10000 = op.apply(this);
            var12 = false;
         } catch (Throwable var13) {
            toThrow = var13;
            var10000 = null;
            var12 = false;
         } finally {
            if (var12) {
               this.closed_$eq(true);
               List rs = this.resources;
               this.resources = null;

               while(rs.nonEmpty()) {
                  Using$Manager$Resource resource = (Using$Manager$Resource)rs.head();
                  rs = (List)rs.tail();

                  try {
                     resource.release();
                  } catch (Throwable var14) {
                     if (toThrow == null) {
                        toThrow = var14;
                     } else {
                        toThrow = Using$.MODULE$.scala$util$Using$$preferentiallySuppress(toThrow, var14);
                     }
                  }
               }

               if (toThrow != null) {
                  throw toThrow;
               }

            }
         }

         Object var3 = var10000;
         this.closed_$eq(true);
         List rs = this.resources;
         this.resources = null;

         while(rs.nonEmpty()) {
            Using$Manager$Resource resource = (Using$Manager$Resource)rs.head();
            rs = (List)rs.tail();

            try {
               resource.release();
            } catch (Throwable var16) {
               if (toThrow == null) {
                  toThrow = var16;
               } else {
                  toThrow = Using$.MODULE$.scala$util$Using$$preferentiallySuppress(toThrow, var16);
               }
            }
         }

         if (toThrow != null) {
            throw toThrow;
         } else {
            return var3;
         }
      }

      public Manager() {
         this.resources = Nil$.MODULE$;
      }
   }

   public static class Manager$ {
      public static final Manager$ MODULE$ = new Manager$();

      public Try apply(final Function1 op) {
         Try$ var10000 = Try$.MODULE$;

         try {
            Object apply_r1 = (new Manager()).scala$util$Using$Manager$$manage(op);
            return new Success(apply_r1);
         } catch (Throwable var4) {
            if (var4 != null && NonFatal$.MODULE$.apply(var4)) {
               return new Failure(var4);
            } else {
               throw var4;
            }
         }
      }

      // $FF: synthetic method
      public static final Object $anonfun$apply$2(final Function1 op$1) {
         return (new Manager()).scala$util$Using$Manager$$manage(op$1);
      }
   }

   public static class Releasable$ {
      public static final Releasable$ MODULE$ = new Releasable$();
   }

   public interface Releasable {
      void release(final Object resource);
   }
}
