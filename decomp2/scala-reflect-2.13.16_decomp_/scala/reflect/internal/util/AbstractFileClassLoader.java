package scala.reflect.internal.util;

import java.io.InputStream;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Constructor;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.security.CodeSource;
import java.security.PermissionCollection;
import java.security.Principal;
import java.security.ProtectionDomain;
import java.security.cert.Certificate;
import java.util.Enumeration;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.collection.immutable.Seq;
import scala.collection.immutable.ArraySeq.;
import scala.collection.mutable.Map;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.io.AbstractFile;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.ObjectRef;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ut!B\u000b\u0017\u0011\u0003yb!B\u0011\u0017\u0011\u0003\u0011\u0003\"B\u0014\u0002\t\u0003A\u0003BB\u0015\u0002\t\u000ba\"F\u0002\u0003\"-\u0001i\u0005\u0002C-\u0005\u0005\u000b\u0007I\u0011\u0001.\t\u0011m#!\u0011!Q\u0001\n1B\u0001\u0002\u0018\u0003\u0003\u0002\u0003\u0006IA\u0014\u0005\u0006O\u0011!\t!\u0018\u0005\u0006C\u0012!\tB\u0019\u0005\u0006K\u0012!\tB\u001a\u0005\u0006Q\u0012!\t\"\u001b\u0005\u0006W\u0012!\t\u0002\u001c\u0005\u0006]\u0012!\tf\u001c\u0005\b\u0003\u0003!A\u0011KA\u0002\u0011\u001d\t\u0019\u0002\u0002C)\u0003+A!\"a\t\u0005\u0011\u000b\u0007I\u0011AA\u0013\u0011!\t\u0019\u0004\u0002Q\u0001\n\u0005U\u0002bBA&\t\u0011\u0005\u0013Q\n\u0005\b\u0003[\"A\u0011IA8\u0011\u001d\t\u0019\b\u0002C!\u0003k\nq#\u00112tiJ\f7\r\u001e$jY\u0016\u001cE.Y:t\u0019>\fG-\u001a:\u000b\u0005]A\u0012\u0001B;uS2T!!\u0007\u000e\u0002\u0011%tG/\u001a:oC2T!a\u0007\u000f\u0002\u000fI,g\r\\3di*\tQ$A\u0003tG\u0006d\u0017m\u0001\u0001\u0011\u0005\u0001\nQ\"\u0001\f\u0003/\u0005\u00137\u000f\u001e:bGR4\u0015\u000e\\3DY\u0006\u001c8\u000fT8bI\u0016\u00148CA\u0001$!\t!S%D\u0001\u001d\u0013\t1CD\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003}\t!\u0002\\8pWV\u0004\b+\u0019;i)\tY3\nF\u0002-e\u0019\u0003\"!\f\u0019\u000e\u00039R!a\f\u000e\u0002\u0005%|\u0017BA\u0019/\u00051\t%m\u001d;sC\u000e$h)\u001b7f\u0011\u0015\u00194\u00011\u00015\u0003%\u0001\u0018\r\u001e5QCJ$8\u000fE\u00026qmr!\u0001\n\u001c\n\u0005]b\u0012a\u00029bG.\fw-Z\u0005\u0003si\u00121aU3r\u0015\t9D\u0004\u0005\u0002=\u0007:\u0011Q(\u0011\t\u0003}qi\u0011a\u0010\u0006\u0003\u0001z\ta\u0001\u0010:p_Rt\u0014B\u0001\"\u001d\u0003\u0019\u0001&/\u001a3fM&\u0011A)\u0012\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\tc\u0002\"B$\u0004\u0001\u0004A\u0015!\u00033je\u0016\u001cGo\u001c:z!\t!\u0013*\u0003\u0002K9\t9!i\\8mK\u0006t\u0007\"\u0002'\u0004\u0001\u0004a\u0013\u0001\u00022bg\u0016\u001c2\u0001\u0002(W!\tyE+D\u0001Q\u0015\t\t&+\u0001\u0003mC:<'\"A*\u0002\t)\fg/Y\u0005\u0003+B\u00131b\u00117bgNdu.\u00193feB\u0011\u0001eV\u0005\u00031Z\u0011\u0001cU2bY\u0006\u001cE.Y:t\u0019>\fG-\u001a:\u0002\tI|w\u000e^\u000b\u0002Y\u0005)!o\\8uA\u00051\u0001/\u0019:f]R$2AX0a!\t\u0001C\u0001C\u0003Z\u0011\u0001\u0007A\u0006C\u0003]\u0011\u0001\u0007a*A\bdY\u0006\u001c8OT1nKR{\u0007+\u0019;i)\tY4\rC\u0003e\u0013\u0001\u00071(\u0001\u0003oC6,\u0017\u0001\u00054j]\u0012\f%m\u001d;sC\u000e$h)\u001b7f)\tas\rC\u0003e\u0015\u0001\u00071(A\u0007eSJt\u0015-\\3U_B\u000bG\u000f\u001b\u000b\u0003w)DQ\u0001Z\u0006A\u0002m\nqBZ5oI\u0006\u00137\u000f\u001e:bGR$\u0015N\u001d\u000b\u0003Y5DQ\u0001\u001a\u0007A\u0002m\n\u0011BZ5oI\u000ec\u0017m]:\u0015\u0005A|\bGA9w!\ra$\u000f^\u0005\u0003g\u0016\u0013Qa\u00117bgN\u0004\"!\u001e<\r\u0001\u0011Iq/DA\u0001\u0002\u0003\u0015\t\u0001\u001f\u0002\u0004?\u0012\n\u0014CA=}!\t!#0\u0003\u0002|9\t9aj\u001c;iS:<\u0007C\u0001\u0013~\u0013\tqHDA\u0002B]fDQ\u0001Z\u0007A\u0002m\nABZ5oIJ+7o\\;sG\u0016$B!!\u0002\u0002\u0012A!\u0011qAA\u0007\u001b\t\tIAC\u0002\u0002\fI\u000b1A\\3u\u0013\u0011\ty!!\u0003\u0003\u0007U\u0013F\nC\u0003e\u001d\u0001\u00071(A\u0007gS:$'+Z:pkJ\u001cWm\u001d\u000b\u0005\u0003/\t\t\u0003\u0005\u0004\u0002\u001a\u0005u\u0011QA\u0007\u0003\u00037Q!a\u0006*\n\t\u0005}\u00111\u0004\u0002\f\u000b:,X.\u001a:bi&|g\u000eC\u0003e\u001f\u0001\u00071(\u0001\tqe>$Xm\u0019;j_:$u.\\1j]V\u0011\u0011q\u0005\t\u0005\u0003S\ty#\u0004\u0002\u0002,)\u0019\u0011Q\u0006*\u0002\u0011M,7-\u001e:jifLA!!\r\u0002,\t\u0001\u0002K]8uK\u000e$\u0018n\u001c8E_6\f\u0017N\\\u0001\ta\u0006\u001c7.Y4fgB9\u0011qGA!w\u0005\u0015SBAA\u001d\u0015\u0011\tY$!\u0010\u0002\u000f5,H/\u00192mK*\u0019\u0011q\b\u000f\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002D\u0005e\"aA'baB\u0019q*a\u0012\n\u0007\u0005%\u0003KA\u0004QC\u000e\\\u0017mZ3\u0002\u001b\u0011,g-\u001b8f!\u0006\u001c7.Y4f)I\t)%a\u0014\u0002R\u0005U\u0013\u0011LA/\u0003C\n)'!\u001b\t\u000b\u0011\u0014\u0002\u0019A\u001e\t\r\u0005M#\u00031\u0001<\u0003%\u0019\b/Z2USRdW\r\u0003\u0004\u0002XI\u0001\raO\u0001\fgB,7MV3sg&|g\u000e\u0003\u0004\u0002\\I\u0001\raO\u0001\u000bgB,7MV3oI>\u0014\bBBA0%\u0001\u00071(A\u0005j[BdG+\u001b;mK\"1\u00111\r\nA\u0002m\n1\"[7qYZ+'o]5p]\"1\u0011q\r\nA\u0002m\n!\"[7qYZ+g\u000eZ8s\u0011\u001d\tYG\u0005a\u0001\u0003\u000b\t\u0001b]3bY\n\u000b7/Z\u0001\u000bO\u0016$\b+Y2lC\u001e,G\u0003BA#\u0003cBQ\u0001Z\nA\u0002m\n1bZ3u!\u0006\u001c7.Y4fgR\u0011\u0011q\u000f\t\u0006I\u0005e\u0014QI\u0005\u0004\u0003wb\"!B!se\u0006L\b"
)
public class AbstractFileClassLoader extends ClassLoader implements ScalaClassLoader {
   private ProtectionDomain protectionDomain;
   private final AbstractFile root;
   private final Map packages;
   private volatile boolean bitmap$0;

   public Object asContext(final Function0 action) {
      return ScalaClassLoader.asContext$(this, action);
   }

   public Option tryToLoadClass(final String path) {
      return ScalaClassLoader.tryToLoadClass$(this, path);
   }

   public Option tryToInitializeClass(final String path) {
      return ScalaClassLoader.tryToInitializeClass$(this, path);
   }

   public Object create(final String path) {
      return ScalaClassLoader.create$(this, path);
   }

   public Object create(final String path, final Function1 errorFn, final Seq args, final ClassTag evidence$2) {
      return ScalaClassLoader.create$(this, path, errorFn, args, evidence$2);
   }

   public byte[] classBytes(final String className) {
      return ScalaClassLoader.classBytes$(this, className);
   }

   public InputStream classAsStream(final String className) {
      return ScalaClassLoader.classAsStream$(this, className);
   }

   public void run(final String objectName, final Seq arguments) {
      ScalaClassLoader.run$(this, objectName, arguments);
   }

   public AbstractFile root() {
      return this.root;
   }

   public String classNameToPath(final String name) {
      return name.endsWith(".class") ? name : (new StringBuilder(6)).append(name.replace('.', '/')).append(".class").toString();
   }

   public AbstractFile findAbstractFile(final String name) {
      return AbstractFileClassLoader$.MODULE$.lookupPath(this.root(), .MODULE$.unsafeWrapArray(scala.collection.StringOps..MODULE$.split$extension(name, '/')), false);
   }

   public String dirNameToPath(final String name) {
      return name.replace('.', '/');
   }

   public AbstractFile findAbstractDir(final String name) {
      Object var2 = new Object();

      try {
         AbstractFile var8 = this.root();

         for(Object var7 : scala.collection.StringOps..MODULE$.split$extension(this.dirNameToPath(name), '/')) {
            var8 = var8.lookupName((String)var7, true);
            if (var8 == null) {
               throw new NonLocalReturnControl(var2, (Object)null);
            }
         }

         return var8;
      } catch (NonLocalReturnControl var9) {
         if (var9.key() == var2) {
            return (AbstractFile)var9.value();
         } else {
            throw var9;
         }
      }
   }

   public Class findClass(final String name) {
      byte[] bytes = this.classBytes(name);
      if (bytes.length == 0) {
         throw new ClassNotFoundException(name);
      } else {
         return this.defineClass(name, bytes, 0, bytes.length, this.protectionDomain());
      }
   }

   public URL findResource(final String name) {
      AbstractFile var2 = this.findAbstractFile(name);
      return var2 == null ? null : new URL((URL)null, (new StringBuilder(7)).append("memory:").append(var2.path()).toString(), new URLStreamHandler(var2) {
         public final AbstractFile x1$1;

         public URLConnection openConnection(final URL url) {
            return new URLConnection(url) {
               // $FF: synthetic field
               private final <undefinedtype> $outer;

               public void connect() {
               }

               public InputStream getInputStream() {
                  return this.$outer.x1$1.input();
               }

               public {
                  if (<VAR_NAMELESS_ENCLOSURE> == null) {
                     throw null;
                  } else {
                     this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                  }
               }
            };
         }

         public {
            this.x1$1 = x1$1;
         }
      });
   }

   public Enumeration findResources(final String name) {
      URL var2 = this.findResource(name);
      return var2 == null ? java.util.Collections.enumeration(java.util.Collections.emptyList()) : java.util.Collections.enumeration(java.util.Collections.singleton(var2));
   }

   private ProtectionDomain protectionDomain$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            ProtectionDomain var8;
            label56: {
               URL resource = Thread.currentThread().getContextClassLoader().getResource("scala/runtime/package.class");
               if (resource != null) {
                  String var10001 = resource.getProtocol();
                  String var2 = "jar";
                  if (var10001 != null) {
                     if (var10001.equals(var2)) {
                        String s = resource.getPath();
                        int n = s.lastIndexOf(33);
                        if (n < 0) {
                           var8 = null;
                        } else {
                           String path = s.substring(0, n);
                           var8 = new ProtectionDomain(new CodeSource((new URI(path)).toURL(), (Certificate[])null), (PermissionCollection)null, this, (Principal[])null);
                        }
                        break label56;
                     }
                  }
               }

               var8 = null;
            }

            this.protectionDomain = var8;
            this.bitmap$0 = true;
         }
      } catch (Throwable var7) {
         throw var7;
      }

      return this.protectionDomain;
   }

   public ProtectionDomain protectionDomain() {
      return !this.bitmap$0 ? this.protectionDomain$lzycompute() : this.protectionDomain;
   }

   public Package definePackage(final String name, final String specTitle, final String specVersion, final String specVendor, final String implTitle, final String implVersion, final String implVendor, final URL sealBase) {
      throw new UnsupportedOperationException();
   }

   public Package getPackage(final String name) {
      return this.findAbstractDir(name) == null ? super.getPackage(name) : (Package)this.packages.getOrElseUpdate(name, () -> {
         Constructor ctor = Package.class.getDeclaredConstructor(String.class, String.class, String.class, String.class, String.class, String.class, String.class, URL.class, ClassLoader.class);
         ctor.setAccessible(true);
         return (Package)ctor.newInstance(name, null, null, null, null, null, null, null, this);
      });
   }

   public Package[] getPackages() {
      return (Package[])this.root().iterator().filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$getPackages$1(x$1))).map((dir) -> this.getPackage(dir.name())).toArray(scala.reflect.ClassTag..MODULE$.apply(Package.class));
   }

   // $FF: synthetic method
   public static final void $anonfun$findAbstractDir$1(final ObjectRef file$2, final Object nonLocalReturnKey2$1, final String dirPart) {
      file$2.elem = ((AbstractFile)file$2.elem).lookupName(dirPart, true);
      if ((AbstractFile)file$2.elem == null) {
         throw new NonLocalReturnControl(nonLocalReturnKey2$1, (Object)null);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getPackages$1(final AbstractFile x$1) {
      return x$1.isDirectory();
   }

   public AbstractFileClassLoader(final AbstractFile root, final ClassLoader parent) {
      super(parent);
      this.root = root;
      this.packages = (Map)scala.collection.mutable.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
   }

   // $FF: synthetic method
   public static final Object $anonfun$findAbstractDir$1$adapted(final ObjectRef file$2, final Object nonLocalReturnKey2$1, final String dirPart) {
      $anonfun$findAbstractDir$1(file$2, nonLocalReturnKey2$1, dirPart);
      return BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
