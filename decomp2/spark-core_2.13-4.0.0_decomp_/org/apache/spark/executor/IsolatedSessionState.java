package org.apache.spark.executor;

import org.apache.spark.util.MutableURLClassLoader;
import scala.Option;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I4QAE\n\u0001+mA\u0001B\t\u0001\u0003\u0006\u0004%\t\u0001\n\u0005\ta\u0001\u0011\t\u0011)A\u0005K!A\u0011\u0007\u0001BA\u0002\u0013\u0005!\u0007\u0003\u0005:\u0001\t\u0005\r\u0011\"\u0001;\u0011!\u0001\u0005A!A!B\u0013\u0019\u0004\u0002C!\u0001\u0005\u0003\u0007I\u0011\u0001\"\t\u0011-\u0003!\u00111A\u0005\u00021C\u0001B\u0014\u0001\u0003\u0002\u0003\u0006Ka\u0011\u0005\t\u001f\u0002\u0011)\u0019!C\u0001!\"AA\f\u0001B\u0001B\u0003%\u0011\u000b\u0003\u0005^\u0001\t\u0015\r\u0011\"\u0001Q\u0011!q\u0006A!A!\u0002\u0013\t\u0006\u0002C0\u0001\u0005\u000b\u0007I\u0011\u0001)\t\u0011\u0001\u0004!\u0011!Q\u0001\nEC\u0001\"\u0019\u0001\u0003\u0006\u0004%\tA\u0019\u0005\tM\u0002\u0011\t\u0011)A\u0005G\")q\r\u0001C\u0001Q\n!\u0012j]8mCR,GmU3tg&|gn\u0015;bi\u0016T!\u0001F\u000b\u0002\u0011\u0015DXmY;u_JT!AF\f\u0002\u000bM\u0004\u0018M]6\u000b\u0005aI\u0012AB1qC\u000eDWMC\u0001\u001b\u0003\ry'oZ\n\u0003\u0001q\u0001\"!\b\u0011\u000e\u0003yQ\u0011aH\u0001\u0006g\u000e\fG.Y\u0005\u0003Cy\u0011a!\u00118z%\u00164\u0017aC:fgNLwN\\+V\u0013\u0012\u001b\u0001!F\u0001&!\t1SF\u0004\u0002(WA\u0011\u0001FH\u0007\u0002S)\u0011!fI\u0001\u0007yI|w\u000e\u001e \n\u00051r\u0012A\u0002)sK\u0012,g-\u0003\u0002/_\t11\u000b\u001e:j]\u001eT!\u0001\f\u0010\u0002\u0019M,7o]5p]V+\u0016\n\u0012\u0011\u0002\u001dU\u0014Hn\u00117bgNdu.\u00193feV\t1\u0007\u0005\u00025o5\tQG\u0003\u00027+\u0005!Q\u000f^5m\u0013\tATGA\u000bNkR\f'\r\\3V%2\u001bE.Y:t\u0019>\fG-\u001a:\u0002%U\u0014Hn\u00117bgNdu.\u00193fe~#S-\u001d\u000b\u0003wy\u0002\"!\b\u001f\n\u0005ur\"\u0001B+oSRDqa\u0010\u0003\u0002\u0002\u0003\u00071'A\u0002yIE\nq\"\u001e:m\u00072\f7o\u001d'pC\u0012,'\u000fI\u0001\u0010e\u0016\u0004Hn\u00117bgNdu.\u00193feV\t1\t\u0005\u0002E\u00136\tQI\u0003\u0002G\u000f\u0006!A.\u00198h\u0015\u0005A\u0015\u0001\u00026bm\u0006L!AS#\u0003\u0017\rc\u0017m]:M_\u0006$WM]\u0001\u0014e\u0016\u0004Hn\u00117bgNdu.\u00193fe~#S-\u001d\u000b\u0003w5CqaP\u0004\u0002\u0002\u0003\u00071)\u0001\tsKBd7\t\\1tg2{\u0017\rZ3sA\u0005a1-\u001e:sK:$h)\u001b7fgV\t\u0011\u000b\u0005\u0003S/\u0016JV\"A*\u000b\u0005Q+\u0016aB7vi\u0006\u0014G.\u001a\u0006\u0003-z\t!bY8mY\u0016\u001cG/[8o\u0013\tA6KA\u0004ICNDW*\u00199\u0011\u0005uQ\u0016BA.\u001f\u0005\u0011auN\\4\u0002\u001b\r,(O]3oi\u001aKG.Z:!\u0003-\u0019WO\u001d:f]RT\u0015M]:\u0002\u0019\r,(O]3oi*\u000b'o\u001d\u0011\u0002\u001f\r,(O]3oi\u0006\u00138\r[5wKN\f\u0001cY;se\u0016tG/\u0011:dQ&4Xm\u001d\u0011\u0002\u001fI,\u0007\u000f\\\"mCN\u001cH)\u001b:Ve&,\u0012a\u0019\t\u0004;\u0011,\u0013BA3\u001f\u0005\u0019y\u0005\u000f^5p]\u0006\u0001\"/\u001a9m\u00072\f7o\u001d#jeV\u0013\u0018\u000eI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0011%\\G.\u001c8paF\u0004\"A\u001b\u0001\u000e\u0003MAQAI\tA\u0002\u0015BQ!M\tA\u0002MBQ!Q\tA\u0002\rCQaT\tA\u0002ECQ!X\tA\u0002ECQaX\tA\u0002ECQ!Y\tA\u0002\r\u0004"
)
public class IsolatedSessionState {
   private final String sessionUUID;
   private MutableURLClassLoader urlClassLoader;
   private ClassLoader replClassLoader;
   private final HashMap currentFiles;
   private final HashMap currentJars;
   private final HashMap currentArchives;
   private final Option replClassDirUri;

   public String sessionUUID() {
      return this.sessionUUID;
   }

   public MutableURLClassLoader urlClassLoader() {
      return this.urlClassLoader;
   }

   public void urlClassLoader_$eq(final MutableURLClassLoader x$1) {
      this.urlClassLoader = x$1;
   }

   public ClassLoader replClassLoader() {
      return this.replClassLoader;
   }

   public void replClassLoader_$eq(final ClassLoader x$1) {
      this.replClassLoader = x$1;
   }

   public HashMap currentFiles() {
      return this.currentFiles;
   }

   public HashMap currentJars() {
      return this.currentJars;
   }

   public HashMap currentArchives() {
      return this.currentArchives;
   }

   public Option replClassDirUri() {
      return this.replClassDirUri;
   }

   public IsolatedSessionState(final String sessionUUID, final MutableURLClassLoader urlClassLoader, final ClassLoader replClassLoader, final HashMap currentFiles, final HashMap currentJars, final HashMap currentArchives, final Option replClassDirUri) {
      this.sessionUUID = sessionUUID;
      this.urlClassLoader = urlClassLoader;
      this.replClassLoader = replClassLoader;
      this.currentFiles = currentFiles;
      this.currentJars = currentJars;
      this.currentArchives = currentArchives;
      this.replClassDirUri = replClassDirUri;
      super();
   }
}
