package breeze.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.lang.invoke.SerializedLambda;
import java.util.BitSet;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import scala.StringContext;
import scala.Predef.;
import scala.collection.BuildFrom;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.Builder;
import scala.runtime.ObjectRef;

public final class package$ {
   public static final package$ MODULE$ = new package$();

   public Object readObject(final File loc) {
      return this.readObject(loc, false);
   }

   public Object readObject(final File loc, final boolean ignoreSerialVersionUID) {
      BufferedInputStream stream = new BufferedInputStream(new GZIPInputStream(new FileInputStream(loc)));
      ObjectInputStream oin = this.nonstupidObjectInputStream(stream, ignoreSerialVersionUID);

      Object var10000;
      try {
         var10000 = oin.readObject();
      } finally {
         oin.close();
      }

      return var10000;
   }

   public byte[] serializeToBytes(final Object obj) {
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      ObjectOutputStream objOut = new ObjectOutputStream(out);
      objOut.writeObject(obj);
      objOut.close();
      out.close();
      return out.toByteArray();
   }

   public Object deserializeFromBytes(final byte[] bytes) {
      ByteArrayInputStream in = new ByteArrayInputStream(bytes);
      ObjectInputStream objIn = new ObjectInputStream(in);

      Object var10000;
      try {
         var10000 = objIn.readObject();
      } finally {
         objIn.close();
      }

      return var10000;
   }

   public ObjectInputStream nonstupidObjectInputStream(final InputStream stream, final boolean ignoreSerialVersionUID) {
      return new SerializableLogging(stream, ignoreSerialVersionUID) {
         private transient volatile LazyLogger breeze$util$SerializableLogging$$_the_logger;
         private final boolean ignoreSerialVersionUID$1;

         public LazyLogger logger() {
            return SerializableLogging.logger$(this);
         }

         public LazyLogger breeze$util$SerializableLogging$$_the_logger() {
            return this.breeze$util$SerializableLogging$$_the_logger;
         }

         public void breeze$util$SerializableLogging$$_the_logger_$eq(final LazyLogger x$1) {
            this.breeze$util$SerializableLogging$$_the_logger = x$1;
         }

         public Class resolveClass(final ObjectStreamClass desc) throws IOException, ClassNotFoundException {
            Class var10000;
            try {
               ClassLoader currentTccl = Thread.currentThread().getContextClassLoader();
               var10000 = currentTccl.loadClass(desc.getName());
            } catch (Exception var4) {
               var10000 = super.resolveClass(desc);
            }

            return var10000;
         }

         public ObjectStreamClass readClassDescriptor() {
            ObjectRef resultClassDescriptor = ObjectRef.create(super.readClassDescriptor());
            if (this.ignoreSerialVersionUID$1) {
               ObjectRef localClass = ObjectRef.create((Object)null);

               try {
                  localClass.elem = Class.forName(((ObjectStreamClass)resultClassDescriptor.elem).getName());
               } catch (ClassNotFoundException var11) {
                  this.logger().error(() -> (new StringBuilder(19)).append("No local class for ").append(((ObjectStreamClass)resultClassDescriptor.elem).getName()).toString(), var11);
                  return (ObjectStreamClass)resultClassDescriptor.elem;
               }

               ObjectStreamClass localClassDescriptor = ObjectStreamClass.lookup((Class)localClass.elem);
               if (localClassDescriptor != null) {
                  long localSUID = localClassDescriptor.getSerialVersionUID();
                  long streamSUID = ((ObjectStreamClass)resultClassDescriptor.elem).getSerialVersionUID();
                  if (streamSUID != localSUID) {
                     StringBuffer s = new StringBuffer("Overriding serialized class version mismatch: ");
                     s.append("local serialVersionUID = ").append(localSUID);
                     s.append(" stream serialVersionUID = ").append(streamSUID);
                     InvalidClassException e = new InvalidClassException(s.toString());
                     this.logger().error(() -> (new StringBuilder(64)).append("Potentially Fatal Deserialization Operation while deserializing ").append((Class)localClass.elem).toString(), e);
                     resultClassDescriptor.elem = localClassDescriptor;
                  }
               }
            }

            return (ObjectStreamClass)resultClassDescriptor.elem;
         }

         public {
            this.ignoreSerialVersionUID$1 = ignoreSerialVersionUID$1;
            SerializableLogging.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public boolean nonstupidObjectInputStream$default$2() {
      return false;
   }

   public StringContext FileUtil(final StringContext sc) {
      return sc;
   }

   public void writeObject(final File out, final Object parser) {
      ObjectOutputStream stream = new ObjectOutputStream(new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(out))));
      stream.writeObject(parser);
      stream.close();
   }

   public String LOCATION() {
      StackTraceElement e = (new Exception()).getStackTrace()[1];
      return (new StringBuilder(1)).append(e.getFileName()).append(":").append(e.getLineNumber()).toString();
   }

   public String CALLER(final int nth) {
      StackTraceElement e = (new Exception()).getStackTrace()[nth + 1];
      return (new StringBuilder(1)).append(e.getFileName()).append(":").append(e.getLineNumber()).toString();
   }

   public String memoryString() {
      Runtime r = Runtime.getRuntime();
      long free = r.freeMemory() / 1048576L;
      long total = r.totalMemory() / 1048576L;
      return (new StringBuilder(23)).append(total - free).append("M used; ").append(free).append("M free; ").append(total).append("M total").toString();
   }

   public Object trace(final Object a) {
      .MODULE$.println(a);
      return a;
   }

   public package.SeqExtras SeqExtras(final Seq s) {
      return new package.SeqExtras(s);
   }

   public package.SeqExtras arraySeqExtras(final Object s) {
      return new package.SeqExtras(scala.collection.compat.immutable.package..MODULE$.ArraySeq().unsafeWrapArray(s));
   }

   public BitSet AwesomeBitSet(final BitSet bs) {
      return bs;
   }

   public BuildFrom _bitsetcbf() {
      return new BuildFrom() {
         /** @deprecated */
         public Builder apply(final Object from) {
            return BuildFrom.apply$(this, from);
         }

         public Factory toFactory(final Object from) {
            return BuildFrom.toFactory$(this, from);
         }

         public Set fromSpecific(final BitSet from, final IterableOnce it) {
            return (Set).MODULE$.Set().empty().$plus$plus(it);
         }

         public Builder newBuilder(final BitSet from) {
            return .MODULE$.Set().newBuilder();
         }

         private Builder apply() {
            return .MODULE$.Set().newBuilder();
         }

         public {
            BuildFrom.$init$(this);
         }
      };
   }

   public scala.collection.BitSet AwesomeScalaBitSet(final scala.collection.BitSet bs) {
      return bs;
   }

   private package$() {
   }
}
