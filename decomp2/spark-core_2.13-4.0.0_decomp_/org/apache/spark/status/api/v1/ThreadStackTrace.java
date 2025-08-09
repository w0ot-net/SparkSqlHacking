package org.apache.spark.status.api.v1;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\tUf\u0001\u0002!B\u0001:C\u0001\u0002\u001a\u0001\u0003\u0016\u0004%\t!\u001a\u0005\tS\u0002\u0011\t\u0012)A\u0005M\"A!\u000e\u0001BK\u0002\u0013\u00051\u000e\u0003\u0005u\u0001\tE\t\u0015!\u0003m\u0011!)\bA!f\u0001\n\u00031\b\"CA\u0004\u0001\tE\t\u0015!\u0003x\u0011)\tI\u0001\u0001BK\u0002\u0013\u0005\u00111\u0002\u0005\u000b\u0003+\u0001!\u0011#Q\u0001\n\u00055\u0001BCA\f\u0001\tU\r\u0011\"\u0001\u0002\u001a!Q\u0011\u0011\u0005\u0001\u0003\u0012\u0003\u0006I!a\u0007\t\u0013\u0005\r\u0002A!f\u0001\n\u0003Y\u0007\"CA\u0013\u0001\tE\t\u0015!\u0003m\u0011)\t9\u0003\u0001BK\u0002\u0013\u0005\u0011\u0011\u0006\u0005\u000b\u0003\u000b\u0002!\u0011#Q\u0001\n\u0005-\u0002BCA%\u0001\tU\r\u0011\"\u0001\u0002*!Q\u00111\n\u0001\u0003\u0012\u0003\u0006I!a\u000b\t\u0015\u00055\u0003A!f\u0001\n\u0003\tI\u0003\u0003\u0006\u0002P\u0001\u0011\t\u0012)A\u0005\u0003WA!\"!\u0015\u0001\u0005+\u0007I\u0011AA*\u0011)\t9\u0006\u0001B\tB\u0003%\u0011Q\u000b\u0005\u000b\u00033\u0002!Q3A\u0005\u0002\u0005M\u0003BCA.\u0001\tE\t\u0015!\u0003\u0002V!Q\u0011Q\f\u0001\u0003\u0016\u0004%\t!a\u0018\t\u0015\u0005\u001d\u0004A!E!\u0002\u0013\t\t\u0007\u0003\u0006\u0002j\u0001\u0011)\u001a!C\u0001\u0003?B!\"a\u001b\u0001\u0005#\u0005\u000b\u0011BA1\u0011)\ti\u0007\u0001BK\u0002\u0013\u0005\u0011q\f\u0005\u000b\u0003_\u0002!\u0011#Q\u0001\n\u0005\u0005\u0004BCA9\u0001\tU\r\u0011\"\u0001\u0002t!Q\u00111\u0010\u0001\u0003\u0012\u0003\u0006I!!\u001e\t\u000f\u0005u\u0004\u0001\"\u0001\u0002\u0000!9\u0011\u0011\u0015\u0001\u0005B\u0005\r\u0006\"CAS\u0001\u0005\u0005I\u0011AAT\u0011%\t9\rAI\u0001\n\u0003\tI\rC\u0005\u0002`\u0002\t\n\u0011\"\u0001\u0002b\"I\u0011Q\u001d\u0001\u0012\u0002\u0013\u0005\u0011q\u001d\u0005\n\u0003W\u0004\u0011\u0013!C\u0001\u0003[D\u0011\"!=\u0001#\u0003%\t!a=\t\u0013\u0005]\b!%A\u0005\u0002\u0005\u0005\b\"CA}\u0001E\u0005I\u0011AA~\u0011%\ty\u0010AI\u0001\n\u0003\tY\u0010C\u0005\u0003\u0002\u0001\t\n\u0011\"\u0001\u0002|\"I!1\u0001\u0001\u0012\u0002\u0013\u0005!Q\u0001\u0005\n\u0005\u0013\u0001\u0011\u0013!C\u0001\u0005\u000bA\u0011Ba\u0003\u0001#\u0003%\tA!\u0004\t\u0013\tE\u0001!%A\u0005\u0002\t5\u0001\"\u0003B\n\u0001E\u0005I\u0011\u0001B\u0007\u0011%\u0011)\u0002AI\u0001\n\u0003\u00119\u0002C\u0005\u0003\u001c\u0001\t\t\u0011\"\u0011\u0003\u001e!I!1\u0005\u0001\u0002\u0002\u0013\u0005\u00111\u000f\u0005\n\u0005K\u0001\u0011\u0011!C\u0001\u0005OA\u0011Ba\r\u0001\u0003\u0003%\tE!\u000e\t\u0013\t\r\u0003!!A\u0005\u0002\t\u0015\u0003\"\u0003B%\u0001\u0005\u0005I\u0011\tB&\u0011%\u0011y\u0005AA\u0001\n\u0003\u0012\t\u0006C\u0005\u0003T\u0001\t\t\u0011\"\u0011\u0003V\u001dI!\u0011L!\u0002\u0002#\u0005!1\f\u0004\t\u0001\u0006\u000b\t\u0011#\u0001\u0003^!9\u0011Q\u0010\u001e\u0005\u0002\tU\u0004\"CAQu\u0005\u0005IQ\tB<\u0011%\u0011IHOA\u0001\n\u0003\u0013Y\bC\u0005\u0003\u001cj\n\t\u0011\"!\u0003\u001e\"I!1\u0016\u001e\u0002\u0002\u0013%!Q\u0016\u0002\u0011)\"\u0014X-\u00193Ti\u0006\u001c7\u000e\u0016:bG\u0016T!AQ\"\u0002\u0005Y\f$B\u0001#F\u0003\r\t\u0007/\u001b\u0006\u0003\r\u001e\u000baa\u001d;biV\u001c(B\u0001%J\u0003\u0015\u0019\b/\u0019:l\u0015\tQ5*\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u0019\u0006\u0019qN]4\u0004\u0001M!\u0001aT+Y!\t\u00016+D\u0001R\u0015\u0005\u0011\u0016!B:dC2\f\u0017B\u0001+R\u0005\u0019\te.\u001f*fMB\u0011\u0001KV\u0005\u0003/F\u0013q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002ZC:\u0011!l\u0018\b\u00037zk\u0011\u0001\u0018\u0006\u0003;6\u000ba\u0001\u0010:p_Rt\u0014\"\u0001*\n\u0005\u0001\f\u0016a\u00029bG.\fw-Z\u0005\u0003E\u000e\u0014AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001Y)\u0002\u0011QD'/Z1e\u0013\u0012,\u0012A\u001a\t\u0003!\u001eL!\u0001[)\u0003\t1{gnZ\u0001\ni\"\u0014X-\u00193JI\u0002\n!\u0002\u001e5sK\u0006$g*Y7f+\u0005a\u0007CA7r\u001d\tqw\u000e\u0005\u0002\\#&\u0011\u0001/U\u0001\u0007!J,G-\u001a4\n\u0005I\u001c(AB*ue&twM\u0003\u0002q#\u0006YA\u000f\u001b:fC\u0012t\u0015-\\3!\u0003-!\bN]3bIN#\u0018\r^3\u0016\u0003]\u00042\u0001_A\u0001\u001d\tIh0D\u0001{\u0015\tYH0\u0001\u0003mC:<'\"A?\u0002\t)\fg/Y\u0005\u0003\u007fj\fa\u0001\u00165sK\u0006$\u0017\u0002BA\u0002\u0003\u000b\u0011Qa\u0015;bi\u0016T!a >\u0002\u0019QD'/Z1e'R\fG/\u001a\u0011\u0002\u0015M$\u0018mY6Ue\u0006\u001cW-\u0006\u0002\u0002\u000eA!\u0011qBA\t\u001b\u0005\t\u0015bAA\n\u0003\nQ1\u000b^1dWR\u0013\u0018mY3\u0002\u0017M$\u0018mY6Ue\u0006\u001cW\rI\u0001\u0012E2|7m[3e\u0005f$\u0006N]3bI&#WCAA\u000e!\u0011\u0001\u0016Q\u00044\n\u0007\u0005}\u0011K\u0001\u0004PaRLwN\\\u0001\u0013E2|7m[3e\u0005f$\u0006N]3bI&#\u0007%A\u0007cY>\u001c7.\u001a3Cs2{7m[\u0001\u000fE2|7m[3e\u0005fdunY6!\u00031Aw\u000e\u001c3j]\u001edunY6t+\t\tY\u0003\u0005\u0003Z\u0003[a\u0017bAA\u0018G\n\u00191+Z9)\u00175\t\u0019$!\u000f\u0002<\u0005}\u0012\u0011\t\t\u0004!\u0006U\u0012bAA\u001c#\nQA-\u001a9sK\u000e\fG/\u001a3\u0002\u000f5,7o]1hK\u0006\u0012\u0011QH\u0001)kNLgn\u001a\u0011ts:\u001c\u0007N]8oSj,'o\u001d\u0011b]\u0012\u0004Sn\u001c8ji>\u00148\u000fI5ogR,\u0017\rZ\u0001\u0006g&t7-Z\u0011\u0003\u0003\u0007\nQ\u0001\u000e\u00181]A\nQ\u0002[8mI&tw\rT8dWN\u0004\u0003f\u0003\b\u00024\u0005e\u00121HA \u0003\u0003\nQb]=oG\"\u0014xN\\5{KJ\u001c\u0018AD:z]\u000eD'o\u001c8ju\u0016\u00148\u000fI\u0001\t[>t\u0017\u000e^8sg\u0006IQn\u001c8ji>\u00148\u000fI\u0001\tY>\u001c7NT1nKV\u0011\u0011Q\u000b\t\u0005!\u0006uA.A\u0005m_\u000e\\g*Y7fA\u0005iAn\\2l\u001f^tWM\u001d(b[\u0016\fa\u0002\\8dW>;h.\u001a:OC6,\u0007%A\u0005tkN\u0004XM\u001c3fIV\u0011\u0011\u0011\r\t\u0004!\u0006\r\u0014bAA3#\n9!i\\8mK\u0006t\u0017AC:vgB,g\u000eZ3eA\u0005A\u0011N\u001c(bi&4X-A\u0005j]:\u000bG/\u001b<fA\u0005A\u0011n\u001d#bK6|g.A\u0005jg\u0012\u000bW-\\8oA\u0005A\u0001O]5pe&$\u00180\u0006\u0002\u0002vA\u0019\u0001+a\u001e\n\u0007\u0005e\u0014KA\u0002J]R\f\u0011\u0002\u001d:j_JLG/\u001f\u0011\u0002\rqJg.\u001b;?)\u0001\n\t)a!\u0002\u0006\u0006\u001d\u0015\u0011RAF\u0003\u001b\u000by)!%\u0002\u0014\u0006U\u0015qSAM\u00037\u000bi*a(\u0011\u0007\u0005=\u0001\u0001C\u0003e?\u0001\u0007a\rC\u0003k?\u0001\u0007A\u000eC\u0003v?\u0001\u0007q\u000fC\u0004\u0002\n}\u0001\r!!\u0004\t\u000f\u0005]q\u00041\u0001\u0002\u001c!1\u00111E\u0010A\u00021Dq!a\n \u0001\u0004\tY\u0003C\u0004\u0002J}\u0001\r!a\u000b\t\u000f\u00055s\u00041\u0001\u0002,!9\u0011\u0011K\u0010A\u0002\u0005U\u0003bBA-?\u0001\u0007\u0011Q\u000b\u0005\b\u0003;z\u0002\u0019AA1\u0011\u001d\tIg\ba\u0001\u0003CBq!!\u001c \u0001\u0004\t\t\u0007C\u0004\u0002r}\u0001\r!!\u001e\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012\u0001\\\u0001\u0005G>\u0004\u0018\u0010\u0006\u0011\u0002\u0002\u0006%\u00161VAW\u0003_\u000b\t,a-\u00026\u0006]\u0016\u0011XA^\u0003{\u000by,!1\u0002D\u0006\u0015\u0007b\u00023\"!\u0003\u0005\rA\u001a\u0005\bU\u0006\u0002\n\u00111\u0001m\u0011\u001d)\u0018\u0005%AA\u0002]D\u0011\"!\u0003\"!\u0003\u0005\r!!\u0004\t\u0013\u0005]\u0011\u0005%AA\u0002\u0005m\u0001\u0002CA\u0012CA\u0005\t\u0019\u00017\t\u0013\u0005\u001d\u0012\u0005%AA\u0002\u0005-\u0002\"CA%CA\u0005\t\u0019AA\u0016\u0011%\ti%\tI\u0001\u0002\u0004\tY\u0003C\u0005\u0002R\u0005\u0002\n\u00111\u0001\u0002V!I\u0011\u0011L\u0011\u0011\u0002\u0003\u0007\u0011Q\u000b\u0005\n\u0003;\n\u0003\u0013!a\u0001\u0003CB\u0011\"!\u001b\"!\u0003\u0005\r!!\u0019\t\u0013\u00055\u0014\u0005%AA\u0002\u0005\u0005\u0004\"CA9CA\u0005\t\u0019AA;\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"!a3+\u0007\u0019\fim\u000b\u0002\u0002PB!\u0011\u0011[An\u001b\t\t\u0019N\u0003\u0003\u0002V\u0006]\u0017!C;oG\",7m[3e\u0015\r\tI.U\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BAo\u0003'\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\"!a9+\u00071\fi-\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001a\u0016\u0005\u0005%(fA<\u0002N\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\"TCAAxU\u0011\ti!!4\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%kU\u0011\u0011Q\u001f\u0016\u0005\u00037\ti-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001c\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%oU\u0011\u0011Q \u0016\u0005\u0003W\ti-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001d\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%s\u0005y1m\u001c9zI\u0011,g-Y;mi\u0012\n\u0004'\u0006\u0002\u0003\b)\"\u0011QKAg\u0003=\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE\n\u0014aD2paf$C-\u001a4bk2$H%\r\u001a\u0016\u0005\t=!\u0006BA1\u0003\u001b\fqbY8qs\u0012\"WMZ1vYR$\u0013gM\u0001\u0010G>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132i\u0005y1m\u001c9zI\u0011,g-Y;mi\u0012\nT'\u0006\u0002\u0003\u001a)\"\u0011QOAg\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011!q\u0004\t\u0004s\n\u0005\u0012B\u0001:{\u00031\u0001(o\u001c3vGR\f%/\u001b;z\u00039\u0001(o\u001c3vGR,E.Z7f]R$BA!\u000b\u00030A\u0019\u0001Ka\u000b\n\u0007\t5\u0012KA\u0002B]fD\u0011B!\r4\u0003\u0003\u0005\r!!\u001e\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\u00119\u0004\u0005\u0004\u0003:\t}\"\u0011F\u0007\u0003\u0005wQ1A!\u0010R\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0005\u0003\u0012YD\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA1\u0005\u000fB\u0011B!\r6\u0003\u0003\u0005\rA!\u000b\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0005?\u0011i\u0005C\u0005\u00032Y\n\t\u00111\u0001\u0002v\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002v\u00051Q-];bYN$B!!\u0019\u0003X!I!\u0011\u0007\u001d\u0002\u0002\u0003\u0007!\u0011F\u0001\u0011)\"\u0014X-\u00193Ti\u0006\u001c7\u000e\u0016:bG\u0016\u00042!a\u0004;'\u0015Q$q\fB6!\u0001\u0012\tGa\u001agY^\fi!a\u0007m\u0003W\tY#a\u000b\u0002V\u0005U\u0013\u0011MA1\u0003C\n)(!!\u000e\u0005\t\r$b\u0001B3#\u00069!/\u001e8uS6,\u0017\u0002\u0002B5\u0005G\u0012!#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c82kA!!Q\u000eB:\u001b\t\u0011yGC\u0002\u0003rq\f!![8\n\u0007\t\u0014y\u0007\u0006\u0002\u0003\\Q\u0011!qD\u0001\u0006CB\u0004H.\u001f\u000b!\u0003\u0003\u0013iHa \u0003\u0002\n\r%Q\u0011BD\u0005\u0013\u0013YI!$\u0003\u0010\nE%1\u0013BK\u0005/\u0013I\nC\u0003e{\u0001\u0007a\rC\u0003k{\u0001\u0007A\u000eC\u0003v{\u0001\u0007q\u000fC\u0004\u0002\nu\u0002\r!!\u0004\t\u000f\u0005]Q\b1\u0001\u0002\u001c!1\u00111E\u001fA\u00021Dq!a\n>\u0001\u0004\tY\u0003C\u0004\u0002Ju\u0002\r!a\u000b\t\u000f\u00055S\b1\u0001\u0002,!9\u0011\u0011K\u001fA\u0002\u0005U\u0003bBA-{\u0001\u0007\u0011Q\u000b\u0005\b\u0003;j\u0004\u0019AA1\u0011\u001d\tI'\u0010a\u0001\u0003CBq!!\u001c>\u0001\u0004\t\t\u0007C\u0004\u0002ru\u0002\r!!\u001e\u0002\u000fUt\u0017\r\u001d9msR!!q\u0014BT!\u0015\u0001\u0016Q\u0004BQ!u\u0001&1\u00154mo\u00065\u00111\u00047\u0002,\u0005-\u00121FA+\u0003+\n\t'!\u0019\u0002b\u0005U\u0014b\u0001BS#\n9A+\u001e9mKF*\u0004\"\u0003BU}\u0005\u0005\t\u0019AAA\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0005_\u00032!\u001fBY\u0013\r\u0011\u0019L\u001f\u0002\u0007\u001f\nTWm\u0019;"
)
public class ThreadStackTrace implements Product, Serializable {
   private final long threadId;
   private final String threadName;
   private final Thread.State threadState;
   private final StackTrace stackTrace;
   private final Option blockedByThreadId;
   private final String blockedByLock;
   /** @deprecated */
   private final Seq holdingLocks;
   private final Seq synchronizers;
   private final Seq monitors;
   private final Option lockName;
   private final Option lockOwnerName;
   private final boolean suspended;
   private final boolean inNative;
   private final boolean isDaemon;
   private final int priority;

   public static Option unapply(final ThreadStackTrace x$0) {
      return ThreadStackTrace$.MODULE$.unapply(x$0);
   }

   public static ThreadStackTrace apply(final long threadId, final String threadName, final Thread.State threadState, final StackTrace stackTrace, final Option blockedByThreadId, final String blockedByLock, final Seq holdingLocks, final Seq synchronizers, final Seq monitors, final Option lockName, final Option lockOwnerName, final boolean suspended, final boolean inNative, final boolean isDaemon, final int priority) {
      return ThreadStackTrace$.MODULE$.apply(threadId, threadName, threadState, stackTrace, blockedByThreadId, blockedByLock, holdingLocks, synchronizers, monitors, lockName, lockOwnerName, suspended, inNative, isDaemon, priority);
   }

   public static Function1 tupled() {
      return ThreadStackTrace$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ThreadStackTrace$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public long threadId() {
      return this.threadId;
   }

   public String threadName() {
      return this.threadName;
   }

   public Thread.State threadState() {
      return this.threadState;
   }

   public StackTrace stackTrace() {
      return this.stackTrace;
   }

   public Option blockedByThreadId() {
      return this.blockedByThreadId;
   }

   public String blockedByLock() {
      return this.blockedByLock;
   }

   /** @deprecated */
   public Seq holdingLocks() {
      return this.holdingLocks;
   }

   public Seq synchronizers() {
      return this.synchronizers;
   }

   public Seq monitors() {
      return this.monitors;
   }

   public Option lockName() {
      return this.lockName;
   }

   public Option lockOwnerName() {
      return this.lockOwnerName;
   }

   public boolean suspended() {
      return this.suspended;
   }

   public boolean inNative() {
      return this.inNative;
   }

   public boolean isDaemon() {
      return this.isDaemon;
   }

   public int priority() {
      return this.priority;
   }

   public String toString() {
      String daemon = this.isDaemon() ? " daemon" : "";
      String var10002 = this.threadName();
      StringBuilder sb = new StringBuilder("\"" + var10002 + "\"" + daemon + " prio=" + this.priority() + " Id=" + this.threadId() + " " + this.threadState());
      this.lockName().foreach((lock) -> sb.append(" on " + lock));
      this.lockOwnerName().foreach((owner) -> sb.append("owned by \"" + owner + "\""));
      this.blockedByThreadId().foreach((id) -> $anonfun$toString$3(BoxesRunTime.unboxToLong(id)));
      if (this.suspended()) {
         sb.append(" (suspended)");
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      if (this.inNative()) {
         sb.append(" (in native)");
      } else {
         BoxedUnit var3 = BoxedUnit.UNIT;
      }

      sb.append('\n');
      sb.append(((IterableOnceOps)this.stackTrace().elems().map((e) -> "\tat " + e)).mkString());
      if (this.synchronizers().nonEmpty()) {
         sb.append("\n\tNumber of locked synchronizers = " + this.synchronizers().length() + "\n");
         this.synchronizers().foreach((sync) -> sb.append("\t- " + sync + "\n"));
      }

      sb.append('\n');
      return sb.toString();
   }

   public ThreadStackTrace copy(final long threadId, final String threadName, final Thread.State threadState, final StackTrace stackTrace, final Option blockedByThreadId, final String blockedByLock, final Seq holdingLocks, final Seq synchronizers, final Seq monitors, final Option lockName, final Option lockOwnerName, final boolean suspended, final boolean inNative, final boolean isDaemon, final int priority) {
      return new ThreadStackTrace(threadId, threadName, threadState, stackTrace, blockedByThreadId, blockedByLock, holdingLocks, synchronizers, monitors, lockName, lockOwnerName, suspended, inNative, isDaemon, priority);
   }

   public long copy$default$1() {
      return this.threadId();
   }

   public Option copy$default$10() {
      return this.lockName();
   }

   public Option copy$default$11() {
      return this.lockOwnerName();
   }

   public boolean copy$default$12() {
      return this.suspended();
   }

   public boolean copy$default$13() {
      return this.inNative();
   }

   public boolean copy$default$14() {
      return this.isDaemon();
   }

   public int copy$default$15() {
      return this.priority();
   }

   public String copy$default$2() {
      return this.threadName();
   }

   public Thread.State copy$default$3() {
      return this.threadState();
   }

   public StackTrace copy$default$4() {
      return this.stackTrace();
   }

   public Option copy$default$5() {
      return this.blockedByThreadId();
   }

   public String copy$default$6() {
      return this.blockedByLock();
   }

   public Seq copy$default$7() {
      return this.holdingLocks();
   }

   public Seq copy$default$8() {
      return this.synchronizers();
   }

   public Seq copy$default$9() {
      return this.monitors();
   }

   public String productPrefix() {
      return "ThreadStackTrace";
   }

   public int productArity() {
      return 15;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToLong(this.threadId());
         }
         case 1 -> {
            return this.threadName();
         }
         case 2 -> {
            return this.threadState();
         }
         case 3 -> {
            return this.stackTrace();
         }
         case 4 -> {
            return this.blockedByThreadId();
         }
         case 5 -> {
            return this.blockedByLock();
         }
         case 6 -> {
            return this.holdingLocks();
         }
         case 7 -> {
            return this.synchronizers();
         }
         case 8 -> {
            return this.monitors();
         }
         case 9 -> {
            return this.lockName();
         }
         case 10 -> {
            return this.lockOwnerName();
         }
         case 11 -> {
            return BoxesRunTime.boxToBoolean(this.suspended());
         }
         case 12 -> {
            return BoxesRunTime.boxToBoolean(this.inNative());
         }
         case 13 -> {
            return BoxesRunTime.boxToBoolean(this.isDaemon());
         }
         case 14 -> {
            return BoxesRunTime.boxToInteger(this.priority());
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ThreadStackTrace;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "threadId";
         }
         case 1 -> {
            return "threadName";
         }
         case 2 -> {
            return "threadState";
         }
         case 3 -> {
            return "stackTrace";
         }
         case 4 -> {
            return "blockedByThreadId";
         }
         case 5 -> {
            return "blockedByLock";
         }
         case 6 -> {
            return "holdingLocks";
         }
         case 7 -> {
            return "synchronizers";
         }
         case 8 -> {
            return "monitors";
         }
         case 9 -> {
            return "lockName";
         }
         case 10 -> {
            return "lockOwnerName";
         }
         case 11 -> {
            return "suspended";
         }
         case 12 -> {
            return "inNative";
         }
         case 13 -> {
            return "isDaemon";
         }
         case 14 -> {
            return "priority";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.longHash(this.threadId()));
      var1 = Statics.mix(var1, Statics.anyHash(this.threadName()));
      var1 = Statics.mix(var1, Statics.anyHash(this.threadState()));
      var1 = Statics.mix(var1, Statics.anyHash(this.stackTrace()));
      var1 = Statics.mix(var1, Statics.anyHash(this.blockedByThreadId()));
      var1 = Statics.mix(var1, Statics.anyHash(this.blockedByLock()));
      var1 = Statics.mix(var1, Statics.anyHash(this.holdingLocks()));
      var1 = Statics.mix(var1, Statics.anyHash(this.synchronizers()));
      var1 = Statics.mix(var1, Statics.anyHash(this.monitors()));
      var1 = Statics.mix(var1, Statics.anyHash(this.lockName()));
      var1 = Statics.mix(var1, Statics.anyHash(this.lockOwnerName()));
      var1 = Statics.mix(var1, this.suspended() ? 1231 : 1237);
      var1 = Statics.mix(var1, this.inNative() ? 1231 : 1237);
      var1 = Statics.mix(var1, this.isDaemon() ? 1231 : 1237);
      var1 = Statics.mix(var1, this.priority());
      return Statics.finalizeHash(var1, 15);
   }

   public boolean equals(final Object x$1) {
      boolean var24;
      if (this != x$1) {
         label139: {
            if (x$1 instanceof ThreadStackTrace) {
               ThreadStackTrace var4 = (ThreadStackTrace)x$1;
               if (this.threadId() == var4.threadId() && this.suspended() == var4.suspended() && this.inNative() == var4.inNative() && this.isDaemon() == var4.isDaemon() && this.priority() == var4.priority()) {
                  label132: {
                     String var10000 = this.threadName();
                     String var5 = var4.threadName();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label132;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label132;
                     }

                     Thread.State var15 = this.threadState();
                     Thread.State var6 = var4.threadState();
                     if (var15 == null) {
                        if (var6 != null) {
                           break label132;
                        }
                     } else if (!var15.equals(var6)) {
                        break label132;
                     }

                     StackTrace var16 = this.stackTrace();
                     StackTrace var7 = var4.stackTrace();
                     if (var16 == null) {
                        if (var7 != null) {
                           break label132;
                        }
                     } else if (!var16.equals(var7)) {
                        break label132;
                     }

                     Option var17 = this.blockedByThreadId();
                     Option var8 = var4.blockedByThreadId();
                     if (var17 == null) {
                        if (var8 != null) {
                           break label132;
                        }
                     } else if (!var17.equals(var8)) {
                        break label132;
                     }

                     String var18 = this.blockedByLock();
                     String var9 = var4.blockedByLock();
                     if (var18 == null) {
                        if (var9 != null) {
                           break label132;
                        }
                     } else if (!var18.equals(var9)) {
                        break label132;
                     }

                     Seq var19 = this.holdingLocks();
                     Seq var10 = var4.holdingLocks();
                     if (var19 == null) {
                        if (var10 != null) {
                           break label132;
                        }
                     } else if (!var19.equals(var10)) {
                        break label132;
                     }

                     var19 = this.synchronizers();
                     Seq var11 = var4.synchronizers();
                     if (var19 == null) {
                        if (var11 != null) {
                           break label132;
                        }
                     } else if (!var19.equals(var11)) {
                        break label132;
                     }

                     var19 = this.monitors();
                     Seq var12 = var4.monitors();
                     if (var19 == null) {
                        if (var12 != null) {
                           break label132;
                        }
                     } else if (!var19.equals(var12)) {
                        break label132;
                     }

                     Option var22 = this.lockName();
                     Option var13 = var4.lockName();
                     if (var22 == null) {
                        if (var13 != null) {
                           break label132;
                        }
                     } else if (!var22.equals(var13)) {
                        break label132;
                     }

                     var22 = this.lockOwnerName();
                     Option var14 = var4.lockOwnerName();
                     if (var22 == null) {
                        if (var14 != null) {
                           break label132;
                        }
                     } else if (!var22.equals(var14)) {
                        break label132;
                     }

                     if (var4.canEqual(this)) {
                        break label139;
                     }
                  }
               }
            }

            var24 = false;
            return var24;
         }
      }

      var24 = true;
      return var24;
   }

   // $FF: synthetic method
   public static final String $anonfun$toString$3(final long id) {
      return " Id=" + id;
   }

   public ThreadStackTrace(final long threadId, final String threadName, final Thread.State threadState, final StackTrace stackTrace, final Option blockedByThreadId, final String blockedByLock, final Seq holdingLocks, final Seq synchronizers, final Seq monitors, final Option lockName, final Option lockOwnerName, final boolean suspended, final boolean inNative, final boolean isDaemon, final int priority) {
      this.threadId = threadId;
      this.threadName = threadName;
      this.threadState = threadState;
      this.stackTrace = stackTrace;
      this.blockedByThreadId = blockedByThreadId;
      this.blockedByLock = blockedByLock;
      this.holdingLocks = holdingLocks;
      this.synchronizers = synchronizers;
      this.monitors = monitors;
      this.lockName = lockName;
      this.lockOwnerName = lockOwnerName;
      this.suspended = suspended;
      this.inNative = inNative;
      this.isDaemon = isDaemon;
      this.priority = priority;
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
