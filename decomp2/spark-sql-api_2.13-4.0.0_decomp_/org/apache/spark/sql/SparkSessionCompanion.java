package org.apache.spark.sql;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkException.;
import scala.Option;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005]4aAF\f\u0002\u0002]y\u0002\"\u0002\u0014\u0001\t\u0003ACAB\u0016\u0001\u0005\u00039B\u0006C\u00034\u0001\u0011\u0005A\u0007C\u0003=\u0001\u0011\u0005Q\bC\u0003?\u0001\u0011\u0005q\bC\u0003B\u0001\u0011\u0005Q\bC\u0003C\u0001\u0011\u00051\tC\u0003H\u0001\u0011\u00051\tC\u0003I\u0001\u0011\u0005\u0011\nC\u0003K\u0001\u0011%1\nC\u0003N\u0001\u0019Ea\nC\u0003Q\u0001\u0011E\u0011\u000b\u0003\u0004T\u0001\u0011\u0005q\u0003\u0016\u0005\u0006-\u00021\taV\u0004\u00077^A\ta\u0006/\u0007\rY9\u0002\u0012A\f^\u0011\u00151\u0003\u0003\"\u0001_\u0011\u001dy\u0006C1A\u0005\n\u0001Da!\u001b\t!\u0002\u0013\t\u0007b\u00026\u0011\u0005\u0004%Ia\u001b\u0005\u0007mB\u0001\u000b\u0011\u00027\u0003+M\u0003\u0018M]6TKN\u001c\u0018n\u001c8D_6\u0004\u0018M\\5p]*\u0011\u0001$G\u0001\u0004gFd'B\u0001\u000e\u001c\u0003\u0015\u0019\b/\u0019:l\u0015\taR$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002=\u0005\u0019qN]4\u0014\u0005\u0001\u0001\u0003CA\u0011%\u001b\u0005\u0011#\"A\u0012\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0015\u0012#AB!osJ+g-\u0001\u0004=S:LGOP\u0002\u0001)\u0005I\u0003C\u0001\u0016\u0001\u001b\u00059\"aB*fgNLwN\\\t\u0003[A\u0002\"!\t\u0018\n\u0005=\u0012#a\u0002(pi\"Lgn\u001a\t\u0003UEJ!AM\f\u0003\u0019M\u0003\u0018M]6TKN\u001c\u0018n\u001c8\u0002!M,G/Q2uSZ,7+Z:tS>tGCA\u001b9!\t\tc'\u0003\u00028E\t!QK\\5u\u0011\u0015I4\u00011\u0001;\u0003\u001d\u0019Xm]:j_:\u0004\"a\u000f\u0002\u000e\u0003\u0001\t!c\u00197fCJ\f5\r^5wKN+7o]5p]R\tQ'A\ttKR$UMZ1vYR\u001cVm]:j_:$\"!\u000e!\t\u000be*\u0001\u0019\u0001\u001e\u0002'\rdW-\u0019:EK\u001a\fW\u000f\u001c;TKN\u001c\u0018n\u001c8\u0002!\u001d,G/Q2uSZ,7+Z:tS>tW#\u0001#\u0011\u0007\u0005*%(\u0003\u0002GE\t1q\n\u001d;j_:\f\u0011cZ3u\t\u00164\u0017-\u001e7u'\u0016\u001c8/[8o\u0003\u0019\t7\r^5wKV\t!(A\u0007vg\u0006\u0014G.Z*fgNLwN\u001c\u000b\u0003\t2CQ!\u000f\u0006A\u0002A\nq\u0003\u001e:z\u0007\u0006\u001cH\u000fV8J[BdW-\\3oi\u0006$\u0018n\u001c8\u0015\u0005\u0011{\u0005\"B\u001d\f\u0001\u0004\u0001\u0014AG:fi\u0012+g-Y;mi\u0006sG-Q2uSZ,7+Z:tS>tGCA\u001bS\u0011\u0015ID\u00021\u0001;\u00039ygnU3tg&|gn\u00117pg\u0016$\"!N+\t\u000bej\u0001\u0019\u0001\u001e\u0002\u000f\t,\u0018\u000e\u001c3feR\t\u0001\f\u0005\u0002+3&\u0011!l\u0006\u0002\u0014'B\f'o[*fgNLwN\u001c\"vS2$WM]\u0001\u0016'B\f'o[*fgNLwN\\\"p[B\fg.[8o!\tQ\u0003c\u0005\u0002\u0011AQ\tA,A\nbGRLg/\u001a+ie\u0016\fGmU3tg&|g.F\u0001b!\r\u0011w\rM\u0007\u0002G*\u0011A-Z\u0001\u0005Y\u0006twMC\u0001g\u0003\u0011Q\u0017M^1\n\u0005!\u001c'AF%oQ\u0016\u0014\u0018\u000e^1cY\u0016$\u0006N]3bI2{7-\u00197\u0002)\u0005\u001cG/\u001b<f)\"\u0014X-\u00193TKN\u001c\u0018n\u001c8!\u00039!WMZ1vYR\u001cVm]:j_:,\u0012\u0001\u001c\t\u0004[R\u0004T\"\u00018\u000b\u0005=\u0004\u0018AB1u_6L7M\u0003\u0002re\u0006Q1m\u001c8dkJ\u0014XM\u001c;\u000b\u0005M,\u0017\u0001B;uS2L!!\u001e8\u0003\u001f\u0005#x.\\5d%\u00164WM]3oG\u0016\fq\u0002Z3gCVdGoU3tg&|g\u000e\t"
)
public abstract class SparkSessionCompanion {
   public void setActiveSession(final SparkSession session) {
      SparkSessionCompanion$.MODULE$.org$apache$spark$sql$SparkSessionCompanion$$activeThreadSession().set(session);
   }

   public void clearActiveSession() {
      SparkSessionCompanion$.MODULE$.org$apache$spark$sql$SparkSessionCompanion$$activeThreadSession().remove();
   }

   public void setDefaultSession(final SparkSession session) {
      SparkSessionCompanion$.MODULE$.org$apache$spark$sql$SparkSessionCompanion$$defaultSession().set(session);
   }

   public void clearDefaultSession() {
      SparkSessionCompanion$.MODULE$.org$apache$spark$sql$SparkSessionCompanion$$defaultSession().set((Object)null);
   }

   public Option getActiveSession() {
      return this.usableSession((SparkSession)SparkSessionCompanion$.MODULE$.org$apache$spark$sql$SparkSessionCompanion$$activeThreadSession().get());
   }

   public Option getDefaultSession() {
      return this.usableSession((SparkSession)SparkSessionCompanion$.MODULE$.org$apache$spark$sql$SparkSessionCompanion$$defaultSession().get());
   }

   public SparkSession active() {
      return (SparkSession)this.getActiveSession().getOrElse(() -> (SparkSession)this.getDefaultSession().getOrElse(() -> {
            throw .MODULE$.internalError("No active or default Spark session found");
         }));
   }

   private Option usableSession(final SparkSession session) {
      return (Option)(session != null && session.isUsable() ? this.tryCastToImplementation(session) : scala.None..MODULE$);
   }

   public abstract Option tryCastToImplementation(final SparkSession session);

   public void setDefaultAndActiveSession(final SparkSession session) {
      SparkSession currentDefault = (SparkSession)SparkSessionCompanion$.MODULE$.org$apache$spark$sql$SparkSessionCompanion$$defaultSession().getAcquire();
      if (currentDefault != null && currentDefault.isUsable()) {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         BoxesRunTime.boxToBoolean(SparkSessionCompanion$.MODULE$.org$apache$spark$sql$SparkSessionCompanion$$defaultSession().compareAndSet(currentDefault, session));
      }

      Option active = this.getActiveSession();
      if (active.isEmpty() || !((SparkSession)active.get()).isUsable()) {
         this.setActiveSession(session);
      }
   }

   public void onSessionClose(final SparkSession session) {
      SparkSessionCompanion$.MODULE$.org$apache$spark$sql$SparkSessionCompanion$$defaultSession().compareAndSet(session, (Object)null);
      if (this.getActiveSession().contains(session)) {
         this.clearActiveSession();
      }
   }

   public abstract SparkSessionBuilder builder();

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
