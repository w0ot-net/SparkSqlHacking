package scala.reflect.io;

import java.io.Closeable;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00153Q\u0001C\u0005\u0002\u0002AAQA\b\u0001\u0005\u0002}AQA\t\u0001\u0007\u0002\r:Q\u0001L\u0005\t\u000252Q\u0001C\u0005\t\u00029BQA\b\u0003\u0005\u0002MB\u0001\u0002\u000e\u0003\t\u0006\u0004%I!\u000e\u0005\u0006y\u0011!\t!\u0010\u0002\t%>|G\u000fU1uQ*\u0011!bC\u0001\u0003S>T!\u0001D\u0007\u0002\u000fI,g\r\\3di*\ta\"A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0007\u0001\t\u0012\u0004\u0005\u0002\u0013/5\t1C\u0003\u0002\u0015+\u0005!A.\u00198h\u0015\u00051\u0012\u0001\u00026bm\u0006L!\u0001G\n\u0003\r=\u0013'.Z2u!\tQB$D\u0001\u001c\u0015\tQQ#\u0003\u0002\u001e7\tI1\t\\8tK\u0006\u0014G.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\u0001\u0002\"!\t\u0001\u000e\u0003%\tAA]8piV\tA\u0005\u0005\u0002&U5\taE\u0003\u0002(Q\u0005!a-\u001b7f\u0015\tIS#A\u0002oS>L!a\u000b\u0014\u0003\tA\u000bG\u000f[\u0001\t%>|G\u000fU1uQB\u0011\u0011\u0005B\n\u0003\t=\u0002\"\u0001M\u0019\u000e\u00035I!AM\u0007\u0003\r\u0005s\u0017PU3g)\u0005i\u0013!\u00046be\u001a\u001b\bK]8wS\u0012,'/F\u00017!\t9$(D\u00019\u0015\tId%A\u0002ta&L!a\u000f\u001d\u0003%\u0019KG.Z*zgR,W\u000e\u0015:pm&$WM]\u0001\u0006CB\u0004H.\u001f\u000b\u0004Ay\u0002\u0005\"B \b\u0001\u0004!\u0013\u0001\u00029bi\"DQ!Q\u0004A\u0002\t\u000b\u0001b\u001e:ji\u0006\u0014G.\u001a\t\u0003a\rK!\u0001R\u0007\u0003\u000f\t{w\u000e\\3b]\u0002"
)
public abstract class RootPath implements Closeable {
   public static RootPath apply(final java.nio.file.Path path, final boolean writable) {
      return RootPath$.MODULE$.apply(path, writable);
   }

   public abstract java.nio.file.Path root();
}
