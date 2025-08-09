package org.apache.spark.status;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%3a\u0001C\u0005\u0002\u0002-\t\u0002\"\u0002\r\u0001\t\u0003Q\u0002bB\u000f\u0001\u0001\u0004%\tA\b\u0005\bE\u0001\u0001\r\u0011\"\u0001$\u0011\u0019I\u0003\u0001)Q\u0005?!)!\u0006\u0001C\u0001W!9\u0001\bAI\u0001\n\u0003I\u0004\"\u0002#\u0001\r#)%A\u0003'jm\u0016,e\u000e^5us*\u0011!bC\u0001\u0007gR\fG/^:\u000b\u00051i\u0011!B:qCJ\\'B\u0001\b\u0010\u0003\u0019\t\u0007/Y2iK*\t\u0001#A\u0002pe\u001e\u001c\"\u0001\u0001\n\u0011\u0005M1R\"\u0001\u000b\u000b\u0003U\tQa]2bY\u0006L!a\u0006\u000b\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}\r\u0001A#A\u000e\u0011\u0005q\u0001Q\"A\u0005\u0002\u001b1\f7\u000f^,sSR,G+[7f+\u0005y\u0002CA\n!\u0013\t\tCC\u0001\u0003M_:<\u0017!\u00057bgR<&/\u001b;f)&lWm\u0018\u0013fcR\u0011Ae\n\t\u0003'\u0015J!A\n\u000b\u0003\tUs\u0017\u000e\u001e\u0005\bQ\r\t\t\u00111\u0001 \u0003\rAH%M\u0001\u000fY\u0006\u001cHo\u0016:ji\u0016$\u0016.\\3!\u0003\u00159(/\u001b;f)\u0011!C&M\u001a\t\u000b5*\u0001\u0019\u0001\u0018\u0002\u000bM$xN]3\u0011\u0005qy\u0013B\u0001\u0019\n\u0005Q)E.Z7f]R$&/Y2lS:<7\u000b^8sK\")!'\u0002a\u0001?\u0005\u0019an\\<\t\u000fQ*\u0001\u0013!a\u0001k\u0005i1\r[3dWR\u0013\u0018nZ4feN\u0004\"a\u0005\u001c\n\u0005]\"\"a\u0002\"p_2,\u0017M\\\u0001\u0010oJLG/\u001a\u0013eK\u001a\fW\u000f\u001c;%gU\t!H\u000b\u00026w-\nA\b\u0005\u0002>\u00056\taH\u0003\u0002@\u0001\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003\u0003R\t!\"\u00198o_R\fG/[8o\u0013\t\u0019eHA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\f\u0001\u0002Z8Va\u0012\fG/\u001a\u000b\u0002\rB\u00111cR\u0005\u0003\u0011R\u00111!\u00118z\u0001"
)
public abstract class LiveEntity {
   private long lastWriteTime = -1L;

   public long lastWriteTime() {
      return this.lastWriteTime;
   }

   public void lastWriteTime_$eq(final long x$1) {
      this.lastWriteTime = x$1;
   }

   public void write(final ElementTrackingStore store, final long now, final boolean checkTriggers) {
      store.write(this.doUpdate(), checkTriggers || this.lastWriteTime() == -1L);
      this.lastWriteTime_$eq(now);
   }

   public boolean write$default$3() {
      return false;
   }

   public abstract Object doUpdate();
}
