package scala.collection.convert;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055eaB\b\u0011!\u0003\r\ta\u0006\u0005\u00069\u0001!\t!\b\u0005\u0006C\u0001!\tA\t\u0005\u0006y\u0001!\t!\u0010\u0005\u0006C\u0001!\tA\u0012\u0005\u0006'\u0002!\t\u0001\u0016\u0005\u0006C\u0001!\t!\u0018\u0005\u0006C\u0001!\t\u0001\u001c\u0005\u0006C\u0001!\tA\u001e\u0005\u0006C\u0001!\tA \u0005\u0007C\u0001!\t!!\u0005\t\r\u0005\u0002A\u0011AA\u0011\u0011\u001d\ty\u0004\u0001C\u0001\u0003\u0003Ba!\t\u0001\u0005\u0002\u0005]\u0003BB\u0011\u0001\t\u0003\tYG\u0001\tBg*\u000bg/Y\"p]Z,'\u000f^3sg*\u0011\u0011CE\u0001\bG>tg/\u001a:u\u0015\t\u0019B#\u0001\u0006d_2dWm\u0019;j_:T\u0011!F\u0001\u0006g\u000e\fG.Y\u0002\u0001'\t\u0001\u0001\u0004\u0005\u0002\u001a55\tA#\u0003\u0002\u001c)\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$C#\u0001\u0010\u0011\u0005ey\u0012B\u0001\u0011\u0015\u0005\u0011)f.\u001b;\u0002\r\u0005\u001c(*\u0019<b+\t\u0019c\u0006\u0006\u0002%oA\u0019QE\u000b\u0017\u000e\u0003\u0019R!a\n\u0015\u0002\tU$\u0018\u000e\u001c\u0006\u0002S\u0005!!.\u0019<b\u0013\tYcE\u0001\u0005Ji\u0016\u0014\u0018\r^8s!\tic\u0006\u0004\u0001\u0005\u000b=\u0012!\u0019\u0001\u0019\u0003\u0003\u0005\u000b\"!\r\u001b\u0011\u0005e\u0011\u0014BA\u001a\u0015\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!G\u001b\n\u0005Y\"\"aA!os\")\u0001H\u0001a\u0001s\u0005\t\u0011\u000eE\u0002;w1j\u0011AE\u0005\u0003WI\t\u0011#Y:KCZ\fWI\\;nKJ\fG/[8o+\tq4\t\u0006\u0002@\tB\u0019Q\u0005\u0011\"\n\u0005\u00053#aC#ok6,'/\u0019;j_:\u0004\"!L\"\u0005\u000b=\u001a!\u0019\u0001\u0019\t\u000ba\u001a\u0001\u0019A#\u0011\u0007iZ$)\u0006\u0002H\u001fR\u0011\u0001\n\u0015\t\u0004\u00132sU\"\u0001&\u000b\u0005-C\u0013\u0001\u00027b]\u001eL!!\u0014&\u0003\u0011%#XM]1cY\u0016\u0004\"!L(\u0005\u000b=\"!\u0019\u0001\u0019\t\u000ba\"\u0001\u0019A)\u0011\u0007i\u0012f*\u0003\u0002N%\u0005\u0001\u0012m\u001d&bm\u0006\u001cu\u000e\u001c7fGRLwN\\\u000b\u0003+j#\"AV.\u0011\u0007\u0015:\u0016,\u0003\u0002YM\tQ1i\u001c7mK\u000e$\u0018n\u001c8\u0011\u00055RF!B\u0018\u0006\u0005\u0004\u0001\u0004\"\u0002\u001d\u0006\u0001\u0004a\u0006c\u0001\u001eS3V\u0011al\u0019\u000b\u0003?\u0012\u00042!\n1c\u0013\t\tgE\u0001\u0003MSN$\bCA\u0017d\t\u0015ycA1\u00011\u0011\u0015)g\u00011\u0001g\u0003\u0005\u0011\u0007cA4kE6\t\u0001N\u0003\u0002j%\u00059Q.\u001e;bE2,\u0017BA6i\u0005\u0019\u0011UO\u001a4feV\u0011Q\u000e\u001d\u000b\u0003]F\u00042!\n1p!\ti\u0003\u000fB\u00030\u000f\t\u0007\u0001\u0007C\u0003s\u000f\u0001\u00071/A\u0001t!\r9Go\\\u0005\u0003k\"\u00141aU3r+\t9(\u0010\u0006\u0002ywB\u0019Q\u0005Y=\u0011\u00055RH!B\u0018\t\u0005\u0004\u0001\u0004\"\u0002:\t\u0001\u0004a\bc\u0001\u001e~s&\u0011QOE\u000b\u0004\u007f\u0006%A\u0003BA\u0001\u0003\u0017\u0001R!JA\u0002\u0003\u000fI1!!\u0002'\u0005\r\u0019V\r\u001e\t\u0004[\u0005%A!B\u0018\n\u0005\u0004\u0001\u0004B\u0002:\n\u0001\u0004\ti\u0001E\u0003h\u0003\u001f\t9!C\u0002\u0002\u0006!,B!a\u0005\u0002\u001aQ!\u0011QCA\u000e!\u0015)\u00131AA\f!\ri\u0013\u0011\u0004\u0003\u0006_)\u0011\r\u0001\r\u0005\u0007e*\u0001\r!!\b\u0011\u000bi\ny\"a\u0006\n\u0007\u0005\u0015!#\u0006\u0004\u0002$\u00055\u00121\u0007\u000b\u0005\u0003K\t9\u0004E\u0004&\u0003O\tY#!\r\n\u0007\u0005%bEA\u0002NCB\u00042!LA\u0017\t\u0019\tyc\u0003b\u0001a\t\t1\nE\u0002.\u0003g!a!!\u000e\f\u0005\u0004\u0001$!\u0001,\t\u000f\u0005e2\u00021\u0001\u0002<\u0005\tQ\u000eE\u0004h\u0003{\tY#!\r\n\u0007\u0005%\u0002.\u0001\tbg*\u000bg/\u0019#jGRLwN\\1ssV1\u00111IA'\u0003#\"B!!\u0012\u0002TA9Q%a\u0012\u0002L\u0005=\u0013bAA%M\tQA)[2uS>t\u0017M]=\u0011\u00075\ni\u0005\u0002\u0004\u000201\u0011\r\u0001\r\t\u0004[\u0005ECABA\u001b\u0019\t\u0007\u0001\u0007C\u0004\u0002:1\u0001\r!!\u0016\u0011\u000f\u001d\fi$a\u0013\u0002PU1\u0011\u0011LA0\u0003G\"B!a\u0017\u0002fA9Q%a\n\u0002^\u0005\u0005\u0004cA\u0017\u0002`\u00111\u0011qF\u0007C\u0002A\u00022!LA2\t\u0019\t)$\u0004b\u0001a!9\u0011\u0011H\u0007A\u0002\u0005\u001d\u0004c\u0002\u001e\u0002j\u0005u\u0013\u0011M\u0005\u0004\u0003S\u0011RCBA7\u0003{\n\t\t\u0006\u0003\u0002p\u0005\r\u0005\u0003CA9\u0003o\nY(a \u000e\u0005\u0005M$bAA;M\u0005Q1m\u001c8dkJ\u0014XM\u001c;\n\t\u0005e\u00141\u000f\u0002\u000e\u0007>t7-\u001e:sK:$X*\u00199\u0011\u00075\ni\b\u0002\u0004\u000209\u0011\r\u0001\r\t\u0004[\u0005\u0005EABA\u001b\u001d\t\u0007\u0001\u0007C\u0004\u0002:9\u0001\r!!\"\u0011\u0011\u0005\u001d\u00151RA>\u0003\u007fj!!!#\u000b\u0007\u0005U$#\u0003\u0003\u0002*\u0005%\u0005"
)
public interface AsJavaConverters {
   // $FF: synthetic method
   static Iterator asJava$(final AsJavaConverters $this, final scala.collection.Iterator i) {
      return $this.asJava(i);
   }

   default Iterator asJava(final scala.collection.Iterator i) {
      if (i == null) {
         return null;
      } else {
         return (Iterator)(i instanceof JavaCollectionWrappers.JIteratorWrapper ? ((JavaCollectionWrappers.JIteratorWrapper)i).underlying() : new JavaCollectionWrappers.IteratorWrapper(i));
      }
   }

   // $FF: synthetic method
   static Enumeration asJavaEnumeration$(final AsJavaConverters $this, final scala.collection.Iterator i) {
      return $this.asJavaEnumeration(i);
   }

   default Enumeration asJavaEnumeration(final scala.collection.Iterator i) {
      if (i == null) {
         return null;
      } else {
         return (Enumeration)(i instanceof JavaCollectionWrappers.JEnumerationWrapper ? ((JavaCollectionWrappers.JEnumerationWrapper)i).underlying() : new JavaCollectionWrappers.IteratorWrapper(i));
      }
   }

   // $FF: synthetic method
   static Iterable asJava$(final AsJavaConverters $this, final scala.collection.Iterable i) {
      return $this.asJava(i);
   }

   default Iterable asJava(final scala.collection.Iterable i) {
      if (i == null) {
         return null;
      } else {
         return (Iterable)(i instanceof JavaCollectionWrappers.JIterableWrapper ? ((JavaCollectionWrappers.JIterableWrapper)i).underlying() : new JavaCollectionWrappers.IterableWrapper(i));
      }
   }

   // $FF: synthetic method
   static Collection asJavaCollection$(final AsJavaConverters $this, final scala.collection.Iterable i) {
      return $this.asJavaCollection(i);
   }

   default Collection asJavaCollection(final scala.collection.Iterable i) {
      if (i == null) {
         return null;
      } else {
         return (Collection)(i instanceof JavaCollectionWrappers.JCollectionWrapper ? ((JavaCollectionWrappers.JCollectionWrapper)i).underlying() : new JavaCollectionWrappers.IterableWrapper(i));
      }
   }

   // $FF: synthetic method
   static List asJava$(final AsJavaConverters $this, final Buffer b) {
      return $this.asJava(b);
   }

   default List asJava(final Buffer b) {
      if (b == null) {
         return null;
      } else {
         return (List)(b instanceof JavaCollectionWrappers.JListWrapper ? ((JavaCollectionWrappers.JListWrapper)b).underlying() : new JavaCollectionWrappers.MutableBufferWrapper(b));
      }
   }

   // $FF: synthetic method
   static List asJava$(final AsJavaConverters $this, final Seq s) {
      return $this.asJava(s);
   }

   default List asJava(final Seq s) {
      if (s == null) {
         return null;
      } else {
         return (List)(s instanceof JavaCollectionWrappers.JListWrapper ? ((JavaCollectionWrappers.JListWrapper)s).underlying() : new JavaCollectionWrappers.MutableSeqWrapper(s));
      }
   }

   // $FF: synthetic method
   static List asJava$(final AsJavaConverters $this, final scala.collection.Seq s) {
      return $this.asJava(s);
   }

   default List asJava(final scala.collection.Seq s) {
      if (s == null) {
         return null;
      } else {
         return (List)(s instanceof JavaCollectionWrappers.JListWrapper ? ((JavaCollectionWrappers.JListWrapper)s).underlying() : new JavaCollectionWrappers.SeqWrapper(s));
      }
   }

   // $FF: synthetic method
   static Set asJava$(final AsJavaConverters $this, final scala.collection.mutable.Set s) {
      return $this.asJava(s);
   }

   default Set asJava(final scala.collection.mutable.Set s) {
      if (s == null) {
         return null;
      } else {
         return (Set)(s instanceof JavaCollectionWrappers.JSetWrapper ? ((JavaCollectionWrappers.JSetWrapper)s).underlying() : new JavaCollectionWrappers.MutableSetWrapper(s));
      }
   }

   // $FF: synthetic method
   static Set asJava$(final AsJavaConverters $this, final scala.collection.Set s) {
      return $this.asJava(s);
   }

   default Set asJava(final scala.collection.Set s) {
      if (s == null) {
         return null;
      } else {
         return (Set)(s instanceof JavaCollectionWrappers.JSetWrapper ? ((JavaCollectionWrappers.JSetWrapper)s).underlying() : new JavaCollectionWrappers.SetWrapper(s));
      }
   }

   // $FF: synthetic method
   static Map asJava$(final AsJavaConverters $this, final scala.collection.mutable.Map m) {
      return $this.asJava(m);
   }

   default Map asJava(final scala.collection.mutable.Map m) {
      if (m == null) {
         return null;
      } else {
         return (Map)(m instanceof JavaCollectionWrappers.JMapWrapper ? ((JavaCollectionWrappers.JMapWrapper)m).underlying() : new JavaCollectionWrappers.MutableMapWrapper(m));
      }
   }

   // $FF: synthetic method
   static Dictionary asJavaDictionary$(final AsJavaConverters $this, final scala.collection.mutable.Map m) {
      return $this.asJavaDictionary(m);
   }

   default Dictionary asJavaDictionary(final scala.collection.mutable.Map m) {
      if (m == null) {
         return null;
      } else {
         return (Dictionary)(m instanceof JavaCollectionWrappers.JDictionaryWrapper ? ((JavaCollectionWrappers.JDictionaryWrapper)m).underlying() : new JavaCollectionWrappers.DictionaryWrapper(m));
      }
   }

   // $FF: synthetic method
   static Map asJava$(final AsJavaConverters $this, final scala.collection.Map m) {
      return $this.asJava(m);
   }

   default Map asJava(final scala.collection.Map m) {
      if (m == null) {
         return null;
      } else {
         return (Map)(m instanceof JavaCollectionWrappers.JMapWrapper ? ((JavaCollectionWrappers.JMapWrapper)m).underlying() : new JavaCollectionWrappers.MapWrapper(m));
      }
   }

   // $FF: synthetic method
   static ConcurrentMap asJava$(final AsJavaConverters $this, final scala.collection.concurrent.Map m) {
      return $this.asJava(m);
   }

   default ConcurrentMap asJava(final scala.collection.concurrent.Map m) {
      if (m == null) {
         return null;
      } else {
         return (ConcurrentMap)(m instanceof JavaCollectionWrappers.JConcurrentMapWrapper ? ((JavaCollectionWrappers.JConcurrentMapWrapper)m).underlying() : new JavaCollectionWrappers.ConcurrentMapWrapper(m));
      }
   }

   static void $init$(final AsJavaConverters $this) {
   }
}
