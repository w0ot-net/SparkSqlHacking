package scala.reflect.internal.util;

import java.io.IOException;
import java.io.Writer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import scala.MatchError;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Promise;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.util.Failure;
import scala.util.PropertiesTrait;
import scala.util.Success;
import scala.util.Try;
import scala.util.Properties.;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u001dt!B A\u0011\u0003Ie!B&A\u0011\u0003a\u0005\"B)\u0002\t\u0003\u0011\u0006\"B*\u0002\t\u0003!\u0006\"\u0003B\u0011\u0003E\u0005I\u0011\u0001B\u0012\u0011%\u0011I$AI\u0001\n\u0003\u0011Y\u0004C\u0005\u0003@\u0005\t\n\u0011\"\u0001\u0003B!11+\u0001C\u0001\u0005\u000bB\u0011Ba\u0013\u0002\u0005\u0004%IA!\u0014\t\u0011\t=\u0013\u0001)A\u0005\u0005\u001f1QaV\u0001\u0002\"aCQ!\u0015\u0006\u0005\u0002\u0005DQA\u0019\u0006\u0007\u0002\r4a!a0\u0002\t\u0005\u0005\u0007\"\u00036\u000e\u0005\u000b\u0007I\u0011AAb\u0011!aWB!A!\u0002\u0013\u0011\bBB)\u000e\t\u0003\t)\rC\u0004\u0002\u00065!\t%a3\t\u000f\u0005\u0015Q\u0002\"\u0011\u0002P\"9\u0011QA\u0007\u0005B\u0005M\u0007bBA\u0003\u001b\u0011\u0005\u0013q\u001c\u0005\b\u0003\u000biA\u0011IAs\u0011\u0019\tY%\u0004C!G\"1\u0011QJ\u0007\u0005B\rDQAY\u0007\u0005B\r<qA!\u0015\u0002\u0011\u0013\u0011\u0019F\u0002\u0004i\u0003!%!Q\u000b\u0005\u0007#j!\tAa\u0016\t\u0011\te#D1A\u0005\nYDqAa\u0017\u001bA\u0003%q\u000f\u0003\u0005\u0003^i\u0011\r\u0011\"\u0003w\u0011\u001d\u0011yF\u0007Q\u0001\n]D\u0011B!\u0019\u001b#\u0003%\tAa\u0019\u0007\t!\fA!\u001b\u0005\tU\u0006\u0012)\u0019!C\u0001W\"AA.\tB\u0001B\u0003%\u0011\f\u0003\u0005nC\t\u0005\t\u0015!\u0003o\u0011\u0015\t\u0016\u0005\"\u0001r\u0011\u001d)\u0018\u00051A\u0005\nYDq!`\u0011A\u0002\u0013%a\u0010C\u0004\u0002\u0004\u0005\u0002\u000b\u0015B<\t\u000f\u0005\u0015\u0011\u0005\"\u0011\u0002\b!1\u0011QB\u0011\u0005\n\rDa!a\u0004\"\t\u00131\bbBA\u0003C\u0011\u0005\u0013\u0011\u0003\u0005\b\u0003\u000b\tC\u0011IA\u0016\u0011\u0015\u0011\u0017\u0005\"\u0001d\u0011\u0019\tY%\tC!G\"1\u0011QJ\u0011\u0005B\r<q!a\u0014\"\u0011\u0013\t\tFB\u0004\u0002V\u0005BI!a\u0016\t\rE\u0013D\u0011AA6\u0011%\tiG\rb\u0001\n\u0013\ty\u0007\u0003\u0005\u0002\u0000I\u0002\u000b\u0011BA9\u0011%\t\tI\rb\u0001\n\u0003\t\u0019\t\u0003\u0005\u0002\u0010J\u0002\u000b\u0011BAC\u0011%\t\tJ\rb\u0001\n\u0013\t\u0019\n\u0003\u0005\u0002\"J\u0002\u000b\u0011BAK\u0011)\t\u0019K\ra\u0001\u0002\u0004%\tA\u001e\u0005\f\u0003K\u0013\u0004\u0019!a\u0001\n\u0003\t9\u000b\u0003\u0006\u0002,J\u0002\r\u0011!Q!\n]Dq!!.3\t\u0003\t9\f\u0003\u0004\u0002>J\"\taY\u0001\n\r&dW-\u0016;jYNT!!\u0011\"\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u0007\u0012\u000b\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003\u000b\u001a\u000bqA]3gY\u0016\u001cGOC\u0001H\u0003\u0015\u00198-\u00197b\u0007\u0001\u0001\"AS\u0001\u000e\u0003\u0001\u0013\u0011BR5mKV#\u0018\u000e\\:\u0014\u0005\u0005i\u0005C\u0001(P\u001b\u00051\u0015B\u0001)G\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\u0012!S\u0001\u0017]\u0016<\u0018i]=oG\n+hMZ3sK\u0012<&/\u001b;feRIQ+!<\u0002~\n-!q\u0003\t\u0003-*i\u0011!\u0001\u0002\u000b\u0019&tWm\u0016:ji\u0016\u00148C\u0001\u0006Z!\tQv,D\u0001\\\u0015\taV,\u0001\u0002j_*\ta,\u0001\u0003kCZ\f\u0017B\u00011\\\u0005\u00199&/\u001b;feR\tQ+A\u0004oK^d\u0015N\\3\u0015\u0003\u0011\u0004\"AT3\n\u0005\u00194%\u0001B+oSRL3AC\u0011\u000e\u0005M\t5/\u001f8d\u0005V4g-\u001a:fI^\u0013\u0018\u000e^3s'\t\tS+\u0001\u0006v]\u0012,'\u000f\\=j]\u001e,\u0012!W\u0001\fk:$WM\u001d7zS:<\u0007%\u0001\u0006ck\u001a4WM]*ju\u0016\u0004\"AT8\n\u0005A4%aA%oiR\u0019!o\u001d;\u0011\u0005Y\u000b\u0003\"\u00026&\u0001\u0004I\u0006bB7&!\u0003\u0005\rA\\\u0001\bGV\u0014(/\u001a8u+\u00059\bC\u0001=|\u001b\u0005I(B\u0001>^\u0003\rq\u0017n\\\u0005\u0003yf\u0014!b\u00115be\n+hMZ3s\u0003-\u0019WO\u001d:f]R|F%Z9\u0015\u0005\u0011|\b\u0002CA\u0001O\u0005\u0005\t\u0019A<\u0002\u0007a$\u0013'\u0001\u0005dkJ\u0014XM\u001c;!\u0003\u00159(/\u001b;f)\r!\u0017\u0011\u0002\u0005\u0007\u0003\u0017I\u0003\u0019\u00018\u0002\u0003\r\f!B\u001a7vg\"\f5/\u001f8d\u0003!\tG\u000e\\8dCR,Gc\u00023\u0002\u0014\u0005\r\u0012q\u0005\u0005\b\u0003+a\u0003\u0019AA\f\u0003\u0011\u0019'-\u001e4\u0011\u000b9\u000bI\"!\b\n\u0007\u0005maIA\u0003BeJ\f\u0017\u0010E\u0002O\u0003?I1!!\tG\u0005\u0011\u0019\u0005.\u0019:\t\r\u0005\u0015B\u00061\u0001o\u00035Ig.\u001b;jC2|eMZ:fi\"1\u0011\u0011\u0006\u0017A\u00029\fQ\"\u001b8ji&\fG\u000eT3oORDGc\u00023\u0002.\u0005\u001d\u0013\u0011\n\u0005\b\u0003_i\u0003\u0019AA\u0019\u0003\u0005\u0019\b\u0003BA\u001a\u0003\u0003rA!!\u000e\u0002>A\u0019\u0011q\u0007$\u000e\u0005\u0005e\"bAA\u001e\u0011\u00061AH]8pizJ1!a\u0010G\u0003\u0019\u0001&/\u001a3fM&!\u00111IA#\u0005\u0019\u0019FO]5oO*\u0019\u0011q\b$\t\r\u0005\u0015R\u00061\u0001o\u0011\u0019\tI#\fa\u0001]\u0006)a\r\\;tQ\u0006)1\r\\8tK\u0006Q!-Y2lOJ|WO\u001c3\u0011\u0007\u0005M#'D\u0001\"\u0005)\u0011\u0017mY6he>,h\u000eZ\n\u0006e\u0005e\u0013Q\r\t\u0005\u00037\n\t'\u0004\u0002\u0002^)\u0019\u0011qL/\u0002\t1\fgnZ\u0005\u0005\u0003G\niF\u0001\u0004PE*,7\r\u001e\t\u0005\u00037\n9'\u0003\u0003\u0002j\u0005u#\u0001\u0003*v]:\f'\r\\3\u0015\u0005\u0005E\u0013a\u00029f]\u0012LgnZ\u000b\u0003\u0003c\u0002R!a\u001d\u0002|]l!!!\u001e\u000b\t\u0005]\u0014\u0011P\u0001\u000bG>t7-\u001e:sK:$(BA!^\u0013\u0011\ti(!\u001e\u0003'1Kgn[3e\u00052|7m[5oOF+X-^3\u0002\u0011A,g\u000eZ5oO\u0002\n1\"Y:z]\u000e\u001cF/\u0019;vgV\u0011\u0011Q\u0011\t\u0006\u0003\u000f\u000bY\tZ\u0007\u0003\u0003\u0013S1!a\u001eG\u0013\u0011\ti)!#\u0003\u000fA\u0013x.\\5tK\u0006a\u0011m]=oGN#\u0018\r^;tA\u0005I1o\u00195fIVdW\rZ\u000b\u0003\u0003+\u0003B!a&\u0002\u001e6\u0011\u0011\u0011\u0014\u0006\u0005\u00037\u000b)(\u0001\u0004bi>l\u0017nY\u0005\u0005\u0003?\u000bIJA\u0007Bi>l\u0017n\u0019\"p_2,\u0017M\\\u0001\u000bg\u000eDW\rZ;mK\u0012\u0004\u0013a\u0003:fkN,')\u001e4gKJ\fqB]3vg\u0016\u0014UO\u001a4fe~#S-\u001d\u000b\u0004I\u0006%\u0006\u0002CA\u0001w\u0005\u0005\t\u0019A<\u0002\u0019I,Wo]3Ck\u001a4WM\u001d\u0011)\u0007q\ny\u000bE\u0002O\u0003cK1!a-G\u0005!1x\u000e\\1uS2,\u0017aD3ogV\u0014X\r\u0015:pG\u0016\u001c8/\u001a3\u0015\u0007\u0011\fI\f\u0003\u0004\u0002<v\u0002\ra^\u0001\u0007EV4g-\u001a:\u0002\u0007I,hN\u0001\tUQJ,\u0017\rZ:bM\u0016<&/\u001b;feN\u0011Q\"V\u000b\u0002eR!\u0011qYAe!\t1V\u0002C\u0003k!\u0001\u0007!\u000fF\u0002e\u0003\u001bDa!a\u0003\u0012\u0001\u0004qGc\u00013\u0002R\"9\u0011Q\u0003\nA\u0002\u0005]Ac\u00023\u0002V\u0006]\u00171\u001c\u0005\b\u0003+\u0019\u0002\u0019AA\f\u0011\u0019\tIn\u0005a\u0001]\u0006\u0019qN\u001a4\t\r\u0005u7\u00031\u0001o\u0003\raWM\u001c\u000b\u0004I\u0006\u0005\bbBAr)\u0001\u0007\u0011\u0011G\u0001\u0004gR\u0014Hc\u00023\u0002h\u0006%\u00181\u001e\u0005\b\u0003G,\u0002\u0019AA\u0019\u0011\u0019\tI.\u0006a\u0001]\"1\u0011Q\\\u000bA\u00029Dq!a<\u0004\u0001\u0004\t\t0\u0001\u0003qCRD\u0007\u0003BAz\u0003sl!!!>\u000b\u0007\u0005]\u00180\u0001\u0003gS2,\u0017\u0002BA~\u0003k\u0014A\u0001U1uQ\"I\u0011q`\u0002\u0011\u0002\u0003\u0007!\u0011A\u0001\bG\"\f'o]3u!\u0011\u0011\u0019Aa\u0002\u000e\u0005\t\u0015!bAA\u0000s&!!\u0011\u0002B\u0003\u0005\u001d\u0019\u0005.\u0019:tKRD\u0011B!\u0004\u0004!\u0003\u0005\rAa\u0004\u0002\u000f=\u0004H/[8ogB)a*!\u0007\u0003\u0012A!\u00111\u001fB\n\u0013\u0011\u0011)\"!>\u0003\u0015=\u0003XM\\(qi&|g\u000eC\u0005\u0003\u001a\r\u0001\n\u00111\u0001\u0003\u001c\u0005QA\u000f\u001b:fC\u0012\u001c\u0018MZ3\u0011\u00079\u0013i\"C\u0002\u0003 \u0019\u0013qAQ8pY\u0016\fg.\u0001\u0011oK^\f5/\u001f8d\u0005V4g-\u001a:fI^\u0013\u0018\u000e^3sI\u0011,g-Y;mi\u0012\u0012TC\u0001B\u0013U\u0011\u0011\tAa\n,\u0005\t%\u0002\u0003\u0002B\u0016\u0005ki!A!\f\u000b\t\t=\"\u0011G\u0001\nk:\u001c\u0007.Z2lK\u0012T1Aa\rG\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0005o\u0011iCA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\f\u0001E\\3x\u0003NLhn\u0019\"vM\u001a,'/\u001a3Xe&$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%gU\u0011!Q\b\u0016\u0005\u0005\u001f\u00119#\u0001\u0011oK^\f5/\u001f8d\u0005V4g-\u001a:fI^\u0013\u0018\u000e^3sI\u0011,g-Y;mi\u0012\"TC\u0001B\"U\u0011\u0011YBa\n\u0015\u000bU\u00139E!\u0013\t\u000b)<\u0001\u0019A-\t\u000f\teq\u00011\u0001\u0003\u001c\u0005QajT0P!RKuJT*\u0016\u0005\t=\u0011a\u0003(P?>\u0003F+S(O'\u0002\n1#Q:z]\u000e\u0014UO\u001a4fe\u0016$wK]5uKJ\u0004\"A\u0016\u000e\u0014\u0005iiEC\u0001B*\u0003\u0015\u0019En\\:f\u0003\u0019\u0019En\\:fA\u0005)a\t\\;tQ\u00061a\t\\;tQ\u0002\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u0012TC\u0001B3U\rq'q\u0005"
)
public final class FileUtils {
   public static LineWriter newAsyncBufferedWriter(final Writer underlying, final boolean threadsafe) {
      return FileUtils$.MODULE$.newAsyncBufferedWriter(underlying, threadsafe);
   }

   public static boolean newAsyncBufferedWriter$default$4() {
      return FileUtils$.MODULE$.newAsyncBufferedWriter$default$4();
   }

   public static OpenOption[] newAsyncBufferedWriter$default$3() {
      return FileUtils$.MODULE$.newAsyncBufferedWriter$default$3();
   }

   public static Charset newAsyncBufferedWriter$default$2() {
      return FileUtils$.MODULE$.newAsyncBufferedWriter$default$2();
   }

   public static LineWriter newAsyncBufferedWriter(final Path path, final Charset charset, final OpenOption[] options, final boolean threadsafe) {
      return FileUtils$.MODULE$.newAsyncBufferedWriter(path, charset, options, threadsafe);
   }

   public abstract static class LineWriter extends Writer {
      public abstract void newLine();
   }

   private static class ThreadsafeWriter extends LineWriter {
      private final AsyncBufferedWriter underlying;

      public AsyncBufferedWriter underlying() {
         return this.underlying;
      }

      public void write(final int c) {
         synchronized(this.lock){}

         try {
            this.underlying().write(c);
         } catch (Throwable var4) {
            throw var4;
         }

      }

      public void write(final char[] cbuf) {
         synchronized(this.lock){}

         try {
            this.underlying().write(cbuf);
         } catch (Throwable var4) {
            throw var4;
         }

      }

      public void write(final char[] cbuf, final int off, final int len) {
         synchronized(this.lock){}

         try {
            this.underlying().write(cbuf, off, len);
         } catch (Throwable var6) {
            throw var6;
         }

      }

      public void write(final String str) {
         synchronized(this.lock){}

         try {
            this.underlying().write(str);
         } catch (Throwable var4) {
            throw var4;
         }

      }

      public void write(final String str, final int off, final int len) {
         synchronized(this.lock){}

         try {
            this.underlying().write(str, off, len);
         } catch (Throwable var6) {
            throw var6;
         }

      }

      public void flush() {
         synchronized(this.lock){}

         try {
            this.underlying().flush();
         } catch (Throwable var3) {
            throw var3;
         }

      }

      public void close() {
         synchronized(this.lock){}

         try {
            this.underlying().close();
         } catch (Throwable var3) {
            throw var3;
         }

      }

      public void newLine() {
         synchronized(this.lock){}

         try {
            this.underlying().newLine();
         } catch (Throwable var3) {
            throw var3;
         }

      }

      public ThreadsafeWriter(final AsyncBufferedWriter underlying) {
         this.underlying = underlying;
         this.lock = underlying;
      }
   }

   private static class AsyncBufferedWriter$ {
      public static final AsyncBufferedWriter$ MODULE$ = new AsyncBufferedWriter$();
      private static final CharBuffer scala$reflect$internal$util$FileUtils$AsyncBufferedWriter$$Close = CharBuffer.allocate(0);
      private static final CharBuffer scala$reflect$internal$util$FileUtils$AsyncBufferedWriter$$Flush = CharBuffer.allocate(0);

      public int $lessinit$greater$default$2() {
         return 4096;
      }

      public CharBuffer scala$reflect$internal$util$FileUtils$AsyncBufferedWriter$$Close() {
         return scala$reflect$internal$util$FileUtils$AsyncBufferedWriter$$Close;
      }

      public CharBuffer scala$reflect$internal$util$FileUtils$AsyncBufferedWriter$$Flush() {
         return scala$reflect$internal$util$FileUtils$AsyncBufferedWriter$$Flush;
      }

      public AsyncBufferedWriter$() {
      }
   }

   private static class AsyncBufferedWriter extends LineWriter {
      private volatile background$ background$module;
      private final Writer underlying;
      private final int bufferSize;
      private CharBuffer current;

      public background$ scala$reflect$internal$util$FileUtils$AsyncBufferedWriter$$background() {
         if (this.background$module == null) {
            this.background$lzycompute$1();
         }

         return this.background$module;
      }

      public Writer underlying() {
         return this.underlying;
      }

      private CharBuffer current() {
         return this.current;
      }

      private void current_$eq(final CharBuffer x$1) {
         this.current = x$1;
      }

      public void write(final int c) {
         super.write(c);
      }

      private void flushAsync() {
         this.scala$reflect$internal$util$FileUtils$AsyncBufferedWriter$$background().ensureProcessed(this.current());
         this.current_$eq(this.allocate());
      }

      private CharBuffer allocate() {
         CharBuffer reused = this.scala$reflect$internal$util$FileUtils$AsyncBufferedWriter$$background().reuseBuffer();
         if (reused == null) {
            return CharBuffer.allocate(this.bufferSize);
         } else {
            this.scala$reflect$internal$util$FileUtils$AsyncBufferedWriter$$background().reuseBuffer_$eq((CharBuffer)null);
            reused.clear();
            return reused;
         }
      }

      public void write(final char[] cbuf, final int initialOffset, final int initialLength) {
         int offset = initialOffset;
         int length = initialLength;

         while(length > 0) {
            int capacity = this.current().remaining();
            if (length <= capacity) {
               this.current().put(cbuf, offset, length);
               length = 0;
            } else {
               this.current().put(cbuf, offset, capacity);
               this.flushAsync();
               length -= capacity;
               offset += capacity;
            }
         }

      }

      public void write(final String s, final int initialOffset, final int initialLength) {
         int offset = initialOffset;
         int length = initialLength;

         while(length > 0) {
            int capacity = this.current().remaining();
            if (length <= capacity) {
               this.current().put(s, offset, offset + length);
               length = 0;
            } else {
               this.current().put(s, offset, offset + capacity);
               this.flushAsync();
               length -= capacity;
               offset += capacity;
            }
         }

      }

      public void newLine() {
         this.write(PropertiesTrait.lineSeparator$(.MODULE$));
      }

      public void flush() {
         this.flushAsync();
      }

      public void close() {
         this.scala$reflect$internal$util$FileUtils$AsyncBufferedWriter$$background().ensureProcessed(this.current());
         this.scala$reflect$internal$util$FileUtils$AsyncBufferedWriter$$background().ensureProcessed(FileUtils.AsyncBufferedWriter$.MODULE$.scala$reflect$internal$util$FileUtils$AsyncBufferedWriter$$Close());
         this.current_$eq((CharBuffer)null);
         scala.concurrent.Await..MODULE$.result(this.scala$reflect$internal$util$FileUtils$AsyncBufferedWriter$$background().asyncStatus().future(), scala.concurrent.duration.Duration..MODULE$.Inf());
         this.underlying().close();
      }

      private final void background$lzycompute$1() {
         synchronized(this){}

         try {
            if (this.background$module == null) {
               this.background$module = new background$();
            }
         } catch (Throwable var2) {
            throw var2;
         }

      }

      public AsyncBufferedWriter(final Writer underlying, final int bufferSize) {
         this.underlying = underlying;
         this.bufferSize = bufferSize;
         this.current = this.allocate();
      }

      private class background$ implements Runnable {
         private final LinkedBlockingQueue pending;
         private final Promise asyncStatus;
         private final AtomicBoolean scheduled;
         private volatile CharBuffer reuseBuffer;
         // $FF: synthetic field
         private final AsyncBufferedWriter $outer;

         private LinkedBlockingQueue pending() {
            return this.pending;
         }

         public Promise asyncStatus() {
            return this.asyncStatus;
         }

         private AtomicBoolean scheduled() {
            return this.scheduled;
         }

         public CharBuffer reuseBuffer() {
            return this.reuseBuffer;
         }

         public void reuseBuffer_$eq(final CharBuffer x$1) {
            this.reuseBuffer = x$1;
         }

         public void ensureProcessed(final CharBuffer buffer) {
            if (!this.asyncStatus().isCompleted()) {
               this.pending().add(buffer);
               if (this.scheduled().compareAndSet(false, true)) {
                  ExecutionContext.Implicits var5 = scala.concurrent.ExecutionContext.Implicits..MODULE$;
                  scala.concurrent.ExecutionContext..MODULE$.global().execute(this.$outer.scala$reflect$internal$util$FileUtils$AsyncBufferedWriter$$background());
               }
            } else {
               Try var2 = (Try)this.asyncStatus().future().value().get();
               if (var2 instanceof Success) {
                  BoxedUnit var3 = (BoxedUnit)((Success)var2).value();
                  BoxedUnit var10000 = BoxedUnit.UNIT;
                  if (var10000 == null) {
                     if (var3 == null) {
                        throw new IllegalStateException("closed");
                     }
                  } else if (var10000.equals(var3)) {
                     throw new IllegalStateException("closed");
                  }
               }

               if (var2 instanceof Failure) {
                  Throwable t = ((Failure)var2).exception();
                  throw new IOException("async failure", t);
               } else {
                  throw new MatchError(var2);
               }
            }
         }

         public void run() {
            try {
               while(!this.pending().isEmpty()) {
                  CharBuffer next = (CharBuffer)this.pending().poll();
                  if (next == FileUtils.AsyncBufferedWriter$.MODULE$.scala$reflect$internal$util$FileUtils$AsyncBufferedWriter$$Flush()) {
                     this.$outer.underlying().flush();
                  } else if (next == FileUtils.AsyncBufferedWriter$.MODULE$.scala$reflect$internal$util$FileUtils$AsyncBufferedWriter$$Close()) {
                     this.$outer.underlying().flush();
                     this.$outer.underlying().close();
                     this.asyncStatus().trySuccess(BoxedUnit.UNIT);
                  } else {
                     char[] array = next.array();
                     next.flip();
                     this.$outer.underlying().write(array, next.arrayOffset() + next.position(), next.limit());
                     this.reuseBuffer_$eq(next);
                  }
               }
            } catch (Throwable var7) {
               this.asyncStatus().tryFailure(var7);
               throw var7;
            } finally {
               this.scheduled().set(false);
            }

            if (!this.pending().isEmpty() && this.scheduled().compareAndSet(false, true)) {
               ExecutionContext.Implicits var10000 = scala.concurrent.ExecutionContext.Implicits..MODULE$;
               scala.concurrent.ExecutionContext..MODULE$.global().execute(this.$outer.scala$reflect$internal$util$FileUtils$AsyncBufferedWriter$$background());
            }
         }

         public background$() {
            if (AsyncBufferedWriter.this == null) {
               throw null;
            } else {
               this.$outer = AsyncBufferedWriter.this;
               super();
               this.pending = new LinkedBlockingQueue();
               Promise var10001 = scala.concurrent.Promise..MODULE$;
               this.asyncStatus = new scala.concurrent.impl.Promise.DefaultPromise();
               this.scheduled = new AtomicBoolean();
            }
         }
      }
   }
}
