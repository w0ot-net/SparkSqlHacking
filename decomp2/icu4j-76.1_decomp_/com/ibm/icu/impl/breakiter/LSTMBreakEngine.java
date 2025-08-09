package com.ibm.icu.impl.breakiter;

import com.ibm.icu.impl.ICUResourceBundle;
import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.lang.UScript;
import com.ibm.icu.text.BreakIterator;
import com.ibm.icu.text.UnicodeSet;
import com.ibm.icu.util.UResourceBundle;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LSTMBreakEngine extends DictionaryBreakEngine {
   private static final byte MIN_WORD = 2;
   private static final byte MIN_WORD_SPAN = 4;
   private final LSTMData fData;
   private int fScript;
   private final Vectorizer fVectorizer;

   private static float[][] make2DArray(int[] data, int start, int d1, int d2) {
      byte[] bytes = new byte[4];
      float[][] result = new float[d1][d2];

      for(int i = 0; i < d1; ++i) {
         for(int j = 0; j < d2; ++j) {
            int d = data[start++];
            bytes[0] = (byte)(d >> 24);
            bytes[1] = (byte)(d >> 16);
            bytes[2] = (byte)(d >> 8);
            bytes[3] = (byte)d;
            result[i][j] = ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN).getFloat();
         }
      }

      return result;
   }

   private static float[] make1DArray(int[] data, int start, int d1) {
      byte[] bytes = new byte[4];
      float[] result = new float[d1];

      for(int i = 0; i < d1; ++i) {
         int d = data[start++];
         bytes[0] = (byte)(d >> 24);
         bytes[1] = (byte)(d >> 16);
         bytes[2] = (byte)(d >> 8);
         bytes[3] = (byte)d;
         result[i] = ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN).getFloat();
      }

      return result;
   }

   private Vectorizer makeVectorizer(LSTMData data) {
      switch (data.fType) {
         case CODE_POINTS:
            return new CodePointsVectorizer(data.fDict);
         case GRAPHEME_CLUSTER:
            return new GraphemeClusterVectorizer(data.fDict);
         default:
            return null;
      }
   }

   public LSTMBreakEngine(int script, UnicodeSet set, LSTMData data) {
      this.setCharacters(set);
      this.fScript = script;
      this.fData = data;
      this.fVectorizer = this.makeVectorizer(this.fData);
   }

   public int hashCode() {
      return this.getClass().hashCode();
   }

   public boolean handles(int c) {
      return this.fScript == UCharacter.getIntPropertyValue(c, 4106);
   }

   private static void addDotProductTo(float[] a, float[][] b, float[] result) {
      assert a.length == b.length;

      assert b[0].length == result.length;

      for(int i = 0; i < result.length; ++i) {
         for(int j = 0; j < a.length; ++j) {
            result[i] += a[j] * b[j][i];
         }
      }

   }

   private static void addTo(float[] a, float[] result) {
      assert a.length == result.length;

      for(int i = 0; i < result.length; ++i) {
         result[i] += a[i];
      }

   }

   private static void hadamardProductTo(float[] a, float[] result) {
      assert a.length == result.length;

      for(int i = 0; i < result.length; ++i) {
         result[i] *= a[i];
      }

   }

   private static void addHadamardProductTo(float[] a, float[] b, float[] result) {
      assert a.length == result.length;

      assert b.length == result.length;

      for(int i = 0; i < result.length; ++i) {
         result[i] += a[i] * b[i];
      }

   }

   private static void sigmoid(float[] result, int start, int length) {
      assert start < result.length;

      assert start + length <= result.length;

      for(int i = start; i < start + length; ++i) {
         result[i] = (float)((double)1.0F / ((double)1.0F + Math.exp((double)(-result[i]))));
      }

   }

   private static void tanh(float[] result, int start, int length) {
      assert start < result.length;

      assert start + length <= result.length;

      for(int i = start; i < start + length; ++i) {
         result[i] = (float)Math.tanh((double)result[i]);
      }

   }

   private static int maxIndex(float[] data) {
      int index = 0;
      float max = data[0];

      for(int i = 1; i < data.length; ++i) {
         if (data[i] > max) {
            max = data[i];
            index = i;
         }
      }

      return index;
   }

   private float[] compute(float[][] W, float[][] U, float[] B, float[] x, float[] h, float[] c) {
      float[] ifco = Arrays.copyOf(B, B.length);
      addDotProductTo(x, W, ifco);
      float[] hU = new float[B.length];
      addDotProductTo(h, U, ifco);
      int hunits = B.length / 4;
      sigmoid(ifco, 0 * hunits, hunits);
      sigmoid(ifco, 1 * hunits, hunits);
      tanh(ifco, 2 * hunits, hunits);
      sigmoid(ifco, 3 * hunits, hunits);
      hadamardProductTo(Arrays.copyOfRange(ifco, hunits, 2 * hunits), c);
      addHadamardProductTo(Arrays.copyOf(ifco, hunits), Arrays.copyOfRange(ifco, 2 * hunits, 3 * hunits), c);
      h = Arrays.copyOf(c, c.length);
      tanh(h, 0, h.length);
      hadamardProductTo(Arrays.copyOfRange(ifco, 3 * hunits, 4 * hunits), h);
      return h;
   }

   public int divideUpDictionaryRange(CharacterIterator fIter, int rangeStart, int rangeEnd, DictionaryBreakEngine.DequeI foundBreaks, boolean isPhraseBreaking) {
      int beginSize = foundBreaks.size();
      if (rangeEnd - rangeStart < 4) {
         return 0;
      } else {
         List<Integer> offsets = new ArrayList(rangeEnd - rangeStart);
         List<Integer> indicies = new ArrayList(rangeEnd - rangeStart);
         this.fVectorizer.vectorize(fIter, rangeStart, rangeEnd, offsets, indicies);
         int inputSeqLength = indicies.size();
         int hunits = this.fData.fForwardU.length;
         float[] c = new float[hunits];
         float[][] hBackward = new float[inputSeqLength][hunits];

         for(int i = inputSeqLength - 1; i >= 0; --i) {
            if (i != inputSeqLength - 1) {
               hBackward[i] = Arrays.copyOf(hBackward[i + 1], hunits);
            }

            hBackward[i] = this.compute(this.fData.fBackwardW, this.fData.fBackwardU, this.fData.fBackwardB, this.fData.fEmbedding[(Integer)indicies.get(i)], hBackward[i], c);
         }

         c = new float[hunits];
         float[] forwardH = new float[hunits];
         float[] both = new float[2 * hunits];

         for(int i = 0; i < inputSeqLength; ++i) {
            forwardH = this.compute(this.fData.fForwardW, this.fData.fForwardU, this.fData.fForwardB, this.fData.fEmbedding[(Integer)indicies.get(i)], forwardH, c);
            System.arraycopy(forwardH, 0, both, 0, hunits);
            System.arraycopy(hBackward[i], 0, both, hunits, hunits);
            float[] logp = Arrays.copyOf(this.fData.fOutputB, this.fData.fOutputB.length);
            addDotProductTo(both, this.fData.fOutputW, logp);
            int current = maxIndex(logp);
            if ((current == LSTMBreakEngine.LSTMClass.BEGIN.ordinal() || current == LSTMBreakEngine.LSTMClass.SINGLE.ordinal()) && i != 0) {
               foundBreaks.push((Integer)offsets.get(i));
            }
         }

         return foundBreaks.size() - beginSize;
      }
   }

   public static LSTMData createData(UResourceBundle bundle) {
      return new LSTMData(bundle);
   }

   private static String defaultLSTM(int script) {
      ICUResourceBundle rb = (ICUResourceBundle)UResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudata/brkitr");
      return rb.getStringWithFallback("lstm/" + UScript.getShortName(script));
   }

   public static LSTMData createData(int script) {
      if (script != 23 && script != 24 && script != 28 && script != 38) {
         return null;
      } else {
         String name = defaultLSTM(script);
         name = name.substring(0, name.indexOf("."));
         UResourceBundle rb = UResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudata/brkitr", name, ICUResourceBundle.ICU_DATA_CLASS_LOADER);
         return createData(rb);
      }
   }

   public static LSTMBreakEngine create(int script, LSTMData data) {
      String setExpr = "[[:" + UScript.getShortName(script) + ":]&[:LineBreak=SA:]]";
      UnicodeSet set = new UnicodeSet();
      set.applyPattern(setExpr);
      set.compact();
      return new LSTMBreakEngine(script, set, data);
   }

   public static enum EmbeddingType {
      UNKNOWN,
      CODE_POINTS,
      GRAPHEME_CLUSTER;
   }

   public static enum LSTMClass {
      BEGIN,
      INSIDE,
      END,
      SINGLE;
   }

   public static class LSTMData {
      public EmbeddingType fType;
      public String fName;
      public Map fDict;
      public float[][] fEmbedding;
      public float[][] fForwardW;
      public float[][] fForwardU;
      public float[] fForwardB;
      public float[][] fBackwardW;
      public float[][] fBackwardU;
      public float[] fBackwardB;
      public float[][] fOutputW;
      public float[] fOutputB;

      private LSTMData() {
      }

      public LSTMData(UResourceBundle rb) {
         int embeddings = rb.get("embeddings").getInt();
         int hunits = rb.get("hunits").getInt();
         this.fType = LSTMBreakEngine.EmbeddingType.UNKNOWN;
         this.fName = rb.get("model").getString();
         String typeString = rb.get("type").getString();
         if (typeString.equals("codepoints")) {
            this.fType = LSTMBreakEngine.EmbeddingType.CODE_POINTS;
         } else if (typeString.equals("graphclust")) {
            this.fType = LSTMBreakEngine.EmbeddingType.GRAPHEME_CLUSTER;
         }

         String[] dict = rb.get("dict").getStringArray();
         int[] data = rb.get("data").getIntVector();
         int dataLen = data.length;
         int numIndex = dict.length;
         this.fDict = new HashMap(numIndex + 1);
         int idx = 0;

         for(String embedding : dict) {
            this.fDict.put(embedding, idx++);
         }

         int mat1Size = (numIndex + 1) * embeddings;
         int mat2Size = embeddings * 4 * hunits;
         int mat3Size = hunits * 4 * hunits;
         int mat4Size = 4 * hunits;
         int mat8Size = 2 * hunits * 4;
         int mat9Size = 4;

         assert dataLen == mat1Size + mat2Size + mat3Size + mat4Size + mat2Size + mat3Size + mat4Size + mat8Size + mat9Size;

         int start = 0;
         this.fEmbedding = LSTMBreakEngine.make2DArray(data, start, numIndex + 1, embeddings);
         start += mat1Size;
         this.fForwardW = LSTMBreakEngine.make2DArray(data, start, embeddings, 4 * hunits);
         start += mat2Size;
         this.fForwardU = LSTMBreakEngine.make2DArray(data, start, hunits, 4 * hunits);
         start += mat3Size;
         this.fForwardB = LSTMBreakEngine.make1DArray(data, start, 4 * hunits);
         start += mat4Size;
         this.fBackwardW = LSTMBreakEngine.make2DArray(data, start, embeddings, 4 * hunits);
         start += mat2Size;
         this.fBackwardU = LSTMBreakEngine.make2DArray(data, start, hunits, 4 * hunits);
         start += mat3Size;
         this.fBackwardB = LSTMBreakEngine.make1DArray(data, start, 4 * hunits);
         start += mat4Size;
         this.fOutputW = LSTMBreakEngine.make2DArray(data, start, 2 * hunits, 4);
         start += mat8Size;
         this.fOutputB = LSTMBreakEngine.make1DArray(data, start, 4);
      }
   }

   abstract class Vectorizer {
      private Map fDict;

      public Vectorizer(Map dict) {
         this.fDict = dict;
      }

      public abstract void vectorize(CharacterIterator var1, int var2, int var3, List var4, List var5);

      protected int getIndex(String token) {
         Integer res = (Integer)this.fDict.get(token);
         return res == null ? this.fDict.size() : res;
      }
   }

   class CodePointsVectorizer extends Vectorizer {
      public CodePointsVectorizer(Map dict) {
         super(dict);
      }

      public void vectorize(CharacterIterator fIter, int rangeStart, int rangeEnd, List offsets, List indicies) {
         fIter.setIndex(rangeStart);

         for(char c = fIter.current(); c != '\uffff' && fIter.getIndex() < rangeEnd; c = fIter.next()) {
            offsets.add(fIter.getIndex());
            indicies.add(this.getIndex(String.valueOf(c)));
         }

      }
   }

   class GraphemeClusterVectorizer extends Vectorizer {
      public GraphemeClusterVectorizer(Map dict) {
         super(dict);
      }

      private String substring(CharacterIterator text, int startPos, int endPos) {
         int saved = text.getIndex();
         text.setIndex(startPos);
         StringBuilder sb = new StringBuilder();

         for(char c = text.current(); c != '\uffff' && text.getIndex() < endPos; c = text.next()) {
            sb.append(c);
         }

         text.setIndex(saved);
         return sb.toString();
      }

      public void vectorize(CharacterIterator text, int startPos, int endPos, List offsets, List indicies) {
         BreakIterator iter = BreakIterator.getCharacterInstance();
         iter.setText(text);
         int last = iter.next(startPos);

         for(int curr = iter.next(); curr != -1 && curr <= endPos; curr = iter.next()) {
            offsets.add(last);
            String segment = this.substring(text, last, curr);
            int index = this.getIndex(segment);
            indicies.add(index);
            last = curr;
         }

      }
   }
}
