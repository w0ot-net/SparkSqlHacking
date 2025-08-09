package org.apache.commons.compress.harmony.pack200;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.commons.compress.utils.ParsingUtils;
import org.objectweb.asm.Label;

public class NewAttributeBands extends BandSet {
   protected List attributeLayoutElements;
   private int[] backwardsCallCounts;
   private final CpBands cpBands;
   private final AttributeDefinitionBands.AttributeDefinition def;
   private boolean usedAtLeastOnce;
   private Integral lastPIntegral;

   public NewAttributeBands(int effort, CpBands cpBands, SegmentHeader header, AttributeDefinitionBands.AttributeDefinition def) throws IOException {
      super(effort, header);
      this.def = def;
      this.cpBands = cpBands;
      this.parseLayout();
   }

   public void addAttribute(NewAttribute attribute) {
      this.usedAtLeastOnce = true;
      InputStream stream = new ByteArrayInputStream(attribute.getBytes());

      for(AttributeLayoutElement attributeLayoutElement : this.attributeLayoutElements) {
         attributeLayoutElement.addAttributeToBand(attribute, stream);
      }

   }

   public String getAttributeName() {
      return this.def.name.getUnderlyingString();
   }

   private BHSDCodec getCodec(String layoutElement) {
      if (layoutElement.indexOf(79) >= 0) {
         return Codec.BRANCH5;
      } else if (layoutElement.indexOf(80) >= 0) {
         return Codec.BCI5;
      } else if (layoutElement.indexOf(83) >= 0 && !layoutElement.contains("KS") && !layoutElement.contains("RS")) {
         return Codec.SIGNED5;
      } else {
         return layoutElement.indexOf(66) >= 0 ? Codec.BYTE1 : Codec.UNSIGNED5;
      }
   }

   public int getFlagIndex() {
      return this.def.index;
   }

   private StringReader getStreamUpToMatchingBracket(StringReader reader) throws IOException {
      StringBuilder sb = new StringBuilder();
      int foundBracket = -1;

      while(foundBracket != 0) {
         int read = reader.read();
         if (read == -1) {
            break;
         }

         char c = (char)read;
         if (c == ']') {
            ++foundBracket;
         }

         if (c == '[') {
            --foundBracket;
         }

         if (foundBracket != 0) {
            sb.append(c);
         }
      }

      return new StringReader(sb.toString());
   }

   public boolean isUsedAtLeastOnce() {
      return this.usedAtLeastOnce;
   }

   public int[] numBackwardsCalls() {
      return this.backwardsCallCounts;
   }

   public void pack(OutputStream outputStream) throws IOException, Pack200Exception {
      for(AttributeLayoutElement attributeLayoutElement : this.attributeLayoutElements) {
         attributeLayoutElement.pack(outputStream);
      }

   }

   private void parseLayout() throws IOException {
      String layout = this.def.layout.getUnderlyingString();
      if (this.attributeLayoutElements == null) {
         this.attributeLayoutElements = new ArrayList();
         StringReader reader = new StringReader(layout);

         AttributeLayoutElement e;
         while((e = this.readNextAttributeElement(reader)) != null) {
            this.attributeLayoutElements.add(e);
         }

         this.resolveCalls();
      }

   }

   private List readBody(StringReader reader) throws IOException {
      List<LayoutElement> layoutElements = new ArrayList();

      LayoutElement e;
      while((e = this.readNextLayoutElement(reader)) != null) {
         layoutElements.add(e);
      }

      return layoutElements;
   }

   private int readInteger(int i, InputStream inputStream) {
      int result = 0;

      for(int j = 0; j < i; ++j) {
         try {
            result = result << 8 | inputStream.read();
         } catch (IOException e) {
            throw new UncheckedIOException("Error reading unknown attribute", e);
         }
      }

      if (i == 1) {
         result = (byte)result;
      }

      if (i == 2) {
         result = (short)result;
      }

      return result;
   }

   private AttributeLayoutElement readNextAttributeElement(StringReader reader) throws IOException {
      reader.mark(1);
      int next = reader.read();
      if (next == -1) {
         return null;
      } else if (next == 91) {
         return new Callable(this.readBody(this.getStreamUpToMatchingBracket(reader)));
      } else {
         reader.reset();
         return this.readNextLayoutElement(reader);
      }
   }

   private LayoutElement readNextLayoutElement(StringReader reader) throws IOException {
      int nextChar = reader.read();
      if (nextChar == -1) {
         return null;
      } else {
         switch (nextChar) {
            case 40:
               int number = this.readNumber(reader);
               reader.read();
               return new Call(number);
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 67:
            case 68:
            case 69:
            case 71:
            case 74:
            case 76:
            case 77:
            case 81:
            case 85:
            default:
               return null;
            case 66:
            case 72:
            case 73:
            case 86:
               return new Integral(new String(new char[]{(char)nextChar}));
            case 70:
            case 83:
               return new Integral(new String(new char[]{(char)nextChar, (char)reader.read()}));
            case 75:
            case 82:
               StringBuilder string = (new StringBuilder("")).append((char)nextChar).append((char)reader.read());
               char nxt = (char)reader.read();
               string.append(nxt);
               if (nxt == 'N') {
                  string.append((char)reader.read());
               }

               return new Reference(string.toString());
            case 78:
               char uint_type = (char)reader.read();
               reader.read();
               String str = this.readUpToMatchingBracket(reader);
               return new Replication("" + uint_type, str);
            case 79:
               reader.mark(1);
               if (reader.read() != 83) {
                  reader.reset();
                  return new Integral("O" + (char)reader.read(), this.lastPIntegral);
               }

               return new Integral("OS" + (char)reader.read(), this.lastPIntegral);
            case 80:
               reader.mark(1);
               if (reader.read() != 79) {
                  reader.reset();
                  this.lastPIntegral = new Integral("P" + (char)reader.read());
                  return this.lastPIntegral;
               }

               this.lastPIntegral = new Integral("PO" + (char)reader.read(), this.lastPIntegral);
               return this.lastPIntegral;
            case 84:
               String int_type = String.valueOf((char)reader.read());
               if (int_type.equals("S")) {
                  int_type = int_type + (char)reader.read();
               }

               List<UnionCase> unionCases = new ArrayList();

               UnionCase c;
               while((c = this.readNextUnionCase(reader)) != null) {
                  unionCases.add(c);
               }

               reader.read();
               reader.read();
               reader.read();
               List<LayoutElement> body = null;
               reader.mark(1);
               char next = (char)reader.read();
               if (next != ']') {
                  reader.reset();
                  body = this.readBody(this.getStreamUpToMatchingBracket(reader));
               }

               return new Union(int_type, unionCases, body);
         }
      }
   }

   private UnionCase readNextUnionCase(StringReader reader) throws IOException {
      reader.mark(2);
      reader.read();
      int next = reader.read();
      char ch = (char)next;
      if (ch != ')' && next != -1) {
         reader.reset();
         reader.read();
         List<Integer> tags = new ArrayList();

         Integer nextTag;
         do {
            nextTag = this.readNumber(reader);
            if (nextTag != null) {
               tags.add(nextTag);
               reader.read();
            }
         } while(nextTag != null);

         reader.read();
         reader.mark(1);
         ch = (char)reader.read();
         if (ch == ']') {
            return new UnionCase(tags);
         } else {
            reader.reset();
            return new UnionCase(tags, this.readBody(this.getStreamUpToMatchingBracket(reader)));
         }
      } else {
         reader.reset();
         return null;
      }
   }

   private Integer readNumber(StringReader stream) throws IOException {
      stream.mark(1);
      char first = (char)stream.read();
      boolean negative = first == '-';
      if (!negative) {
         stream.reset();
      }

      stream.mark(100);

      int i;
      int length;
      for(length = 0; (i = stream.read()) != -1 && Character.isDigit((char)i); ++length) {
      }

      stream.reset();
      if (length == 0) {
         return null;
      } else {
         char[] digits = new char[length];
         int read = stream.read(digits);
         if (read != digits.length) {
            throw new IOException("Error reading from the input stream");
         } else {
            return ParsingUtils.parseIntValue((negative ? "-" : "") + new String(digits));
         }
      }
   }

   private String readUpToMatchingBracket(StringReader reader) throws IOException {
      StringBuilder sb = new StringBuilder();
      int foundBracket = -1;

      while(foundBracket != 0) {
         int read = reader.read();
         if (read == -1) {
            break;
         }

         char c = (char)read;
         if (c == ']') {
            ++foundBracket;
         }

         if (c == '[') {
            --foundBracket;
         }

         if (foundBracket != 0) {
            sb.append(c);
         }
      }

      return sb.toString();
   }

   public void renumberBci(IntList bciRenumbering, Map labelsToOffsets) {
      for(AttributeLayoutElement attributeLayoutElement : this.attributeLayoutElements) {
         attributeLayoutElement.renumberBci(bciRenumbering, labelsToOffsets);
      }

   }

   private void resolveCalls() {
      for(int i = 0; i < this.attributeLayoutElements.size(); ++i) {
         AttributeLayoutElement element = (AttributeLayoutElement)this.attributeLayoutElements.get(i);
         if (element instanceof Callable) {
            Callable callable = (Callable)element;

            for(LayoutElement layoutElement : callable.body) {
               this.resolveCallsForElement(i, callable, layoutElement);
            }
         }
      }

      int backwardsCallableIndex = 0;

      for(AttributeLayoutElement attributeLayoutElement : this.attributeLayoutElements) {
         if (attributeLayoutElement instanceof Callable) {
            Callable callable = (Callable)attributeLayoutElement;
            if (callable.isBackwardsCallable) {
               callable.setBackwardsCallableIndex(backwardsCallableIndex);
               ++backwardsCallableIndex;
            }
         }
      }

      this.backwardsCallCounts = new int[backwardsCallableIndex];
   }

   private void resolveCallsForElement(int i, Callable currentCallable, LayoutElement layoutElement) {
      if (layoutElement instanceof Call) {
         Call call = (Call)layoutElement;
         int index = call.callableIndex;
         if (index == 0) {
            call.setCallable(currentCallable);
         } else if (index > 0) {
            for(int k = i + 1; k < this.attributeLayoutElements.size(); ++k) {
               AttributeLayoutElement el = (AttributeLayoutElement)this.attributeLayoutElements.get(k);
               if (el instanceof Callable) {
                  --index;
                  if (index == 0) {
                     call.setCallable((Callable)el);
                     break;
                  }
               }
            }
         } else {
            for(int k = i - 1; k >= 0; --k) {
               AttributeLayoutElement el = (AttributeLayoutElement)this.attributeLayoutElements.get(k);
               if (el instanceof Callable) {
                  ++index;
                  if (index == 0) {
                     call.setCallable((Callable)el);
                     break;
                  }
               }
            }
         }
      } else if (layoutElement instanceof Replication) {
         for(LayoutElement child : ((Replication)layoutElement).layoutElements) {
            this.resolveCallsForElement(i, currentCallable, child);
         }
      }

   }

   public class Call extends LayoutElement {
      private final int callableIndex;
      private Callable callable;

      public Call(int callableIndex) {
         this.callableIndex = callableIndex;
      }

      public void addAttributeToBand(NewAttribute attribute, InputStream inputStream) {
         this.callable.addAttributeToBand(attribute, inputStream);
         if (this.callableIndex < 1) {
            this.callable.addBackwardsCall();
         }

      }

      public Callable getCallable() {
         return this.callable;
      }

      public int getCallableIndex() {
         return this.callableIndex;
      }

      public void pack(OutputStream outputStream) {
      }

      public void renumberBci(IntList bciRenumbering, Map labelsToOffsets) {
      }

      public void setCallable(Callable callable) {
         this.callable = callable;
         if (this.callableIndex < 1) {
            callable.setBackwardsCallable();
         }

      }
   }

   public class Callable implements AttributeLayoutElement {
      private final List body;
      private boolean isBackwardsCallable;
      private int backwardsCallableIndex;

      public Callable(List body) {
         this.body = body;
      }

      public void addAttributeToBand(NewAttribute attribute, InputStream inputStream) {
         for(AttributeLayoutElement element : this.body) {
            element.addAttributeToBand(attribute, inputStream);
         }

      }

      public void addBackwardsCall() {
         int var10002 = NewAttributeBands.this.backwardsCallCounts[this.backwardsCallableIndex]++;
      }

      public List getBody() {
         return this.body;
      }

      public boolean isBackwardsCallable() {
         return this.isBackwardsCallable;
      }

      public void pack(OutputStream outputStream) throws IOException, Pack200Exception {
         for(AttributeLayoutElement element : this.body) {
            element.pack(outputStream);
         }

      }

      public void renumberBci(IntList bciRenumbering, Map labelsToOffsets) {
         for(AttributeLayoutElement element : this.body) {
            element.renumberBci(bciRenumbering, labelsToOffsets);
         }

      }

      public void setBackwardsCallable() {
         this.isBackwardsCallable = true;
      }

      public void setBackwardsCallableIndex(int backwardsCallableIndex) {
         this.backwardsCallableIndex = backwardsCallableIndex;
      }
   }

   public class Integral extends LayoutElement {
      private final String tag;
      private final List band = new ArrayList();
      private final BHSDCodec defaultCodec;
      private Integral previousIntegral;
      private int previousPValue;

      public Integral(String tag) {
         this.tag = tag;
         this.defaultCodec = NewAttributeBands.this.getCodec(tag);
      }

      public Integral(String tag, Integral previousIntegral) {
         this.tag = tag;
         this.defaultCodec = NewAttributeBands.this.getCodec(tag);
         this.previousIntegral = previousIntegral;
      }

      public void addAttributeToBand(NewAttribute attribute, InputStream inputStream) {
         Object val = null;
         int value = 0;
         if (!this.tag.equals("B") && !this.tag.equals("FB")) {
            if (this.tag.equals("SB")) {
               value = NewAttributeBands.this.readInteger(1, inputStream);
            } else if (!this.tag.equals("H") && !this.tag.equals("FH")) {
               if (this.tag.equals("SH")) {
                  value = NewAttributeBands.this.readInteger(2, inputStream);
               } else if (!this.tag.equals("I") && !this.tag.equals("FI") && !this.tag.equals("SI")) {
                  if (!this.tag.equals("V") && !this.tag.equals("FV") && !this.tag.equals("SV")) {
                     if (!this.tag.startsWith("PO") && !this.tag.startsWith("OS")) {
                        if (this.tag.startsWith("P")) {
                           char uint_type = this.tag.substring(1).toCharArray()[0];
                           int length = this.getLength(uint_type);
                           value = NewAttributeBands.this.readInteger(length, inputStream);
                           val = attribute.getLabel(value);
                           this.previousPValue = value;
                        } else if (this.tag.startsWith("O")) {
                           char uint_type = this.tag.substring(1).toCharArray()[0];
                           int length = this.getLength(uint_type);
                           int var8 = NewAttributeBands.this.readInteger(length, inputStream);
                           value = var8 + this.previousIntegral.previousPValue;
                           val = attribute.getLabel(value);
                           this.previousPValue = value;
                        }
                     } else {
                        char uint_type = this.tag.substring(2).toCharArray()[0];
                        int length = this.getLength(uint_type);
                        int var7 = NewAttributeBands.this.readInteger(length, inputStream);
                        value = var7 + this.previousIntegral.previousPValue;
                        val = attribute.getLabel(value);
                        this.previousPValue = value;
                     }
                  }
               } else {
                  value = NewAttributeBands.this.readInteger(4, inputStream);
               }
            } else {
               value = NewAttributeBands.this.readInteger(2, inputStream) & '\uffff';
            }
         } else {
            value = NewAttributeBands.this.readInteger(1, inputStream) & 255;
         }

         if (val == null) {
            val = value;
         }

         this.band.add(val);
      }

      public String getTag() {
         return this.tag;
      }

      public int latestValue() {
         return (Integer)this.band.get(this.band.size() - 1);
      }

      public void pack(OutputStream outputStream) throws IOException, Pack200Exception {
         PackingUtils.log("Writing new attribute bands...");
         byte[] encodedBand = NewAttributeBands.this.encodeBandInt(this.tag, NewAttributeBands.this.integerListToArray(this.band), this.defaultCodec);
         outputStream.write(encodedBand);
         PackingUtils.log("Wrote " + encodedBand.length + " bytes from " + this.tag + "[" + this.band.size() + "]");
      }

      public void renumberBci(IntList bciRenumbering, Map labelsToOffsets) {
         if (!this.tag.startsWith("O") && !this.tag.startsWith("PO")) {
            if (this.tag.startsWith("P")) {
               for(int i = this.band.size() - 1; i >= 0; --i) {
                  Object label = this.band.get(i);
                  if (label instanceof Integer) {
                     break;
                  }

                  if (label instanceof Label) {
                     this.band.remove(i);
                     Integer bytecodeIndex = (Integer)labelsToOffsets.get(label);
                     this.band.add(i, bciRenumbering.get(bytecodeIndex));
                  }
               }
            }
         } else {
            this.renumberOffsetBci(this.previousIntegral.band, bciRenumbering, labelsToOffsets);
         }

      }

      private void renumberOffsetBci(List relative, IntList bciRenumbering, Map labelsToOffsets) {
         for(int i = this.band.size() - 1; i >= 0; --i) {
            Object label = this.band.get(i);
            if (label instanceof Integer) {
               break;
            }

            if (label instanceof Label) {
               this.band.remove(i);
               Integer bytecodeIndex = (Integer)labelsToOffsets.get(label);
               Integer renumberedOffset = bciRenumbering.get(bytecodeIndex) - (Integer)relative.get(i);
               this.band.add(i, renumberedOffset);
            }
         }

      }
   }

   public abstract class LayoutElement implements AttributeLayoutElement {
      protected int getLength(char uint_type) {
         int length = 0;
         switch (uint_type) {
            case 'B':
               length = 1;
               break;
            case 'H':
               length = 2;
               break;
            case 'I':
               length = 4;
               break;
            case 'V':
               length = 0;
         }

         return length;
      }
   }

   public class Reference extends LayoutElement {
      private final String tag;
      private final List band = new ArrayList();
      private final boolean nullsAllowed;

      public Reference(String tag) {
         this.tag = tag;
         this.nullsAllowed = tag.indexOf(78) != -1;
      }

      public void addAttributeToBand(NewAttribute attribute, InputStream inputStream) {
         int index = NewAttributeBands.this.readInteger(4, inputStream);
         if (this.tag.startsWith("RC")) {
            this.band.add(NewAttributeBands.this.cpBands.getCPClass(attribute.readClass(index)));
         } else if (this.tag.startsWith("RU")) {
            this.band.add(NewAttributeBands.this.cpBands.getCPUtf8(attribute.readUTF8(index)));
         } else if (this.tag.startsWith("RS")) {
            this.band.add(NewAttributeBands.this.cpBands.getCPSignature(attribute.readUTF8(index)));
         } else {
            this.band.add(NewAttributeBands.this.cpBands.getConstant(attribute.readConst(index)));
         }

      }

      public String getTag() {
         return this.tag;
      }

      public void pack(OutputStream outputStream) throws IOException, Pack200Exception {
         int[] ints;
         if (this.nullsAllowed) {
            ints = NewAttributeBands.this.cpEntryOrNullListToArray(this.band);
         } else {
            ints = NewAttributeBands.this.cpEntryListToArray(this.band);
         }

         byte[] encodedBand = NewAttributeBands.this.encodeBandInt(this.tag, ints, Codec.UNSIGNED5);
         outputStream.write(encodedBand);
         PackingUtils.log("Wrote " + encodedBand.length + " bytes from " + this.tag + "[" + ints.length + "]");
      }

      public void renumberBci(IntList bciRenumbering, Map labelsToOffsets) {
      }
   }

   public class Replication extends LayoutElement {
      private final Integral countElement;
      private final List layoutElements = new ArrayList();

      public Replication(String tag, String contents) throws IOException {
         this.countElement = NewAttributeBands.this.new Integral(tag);
         StringReader stream = new StringReader(contents);

         LayoutElement e;
         while((e = NewAttributeBands.this.readNextLayoutElement(stream)) != null) {
            this.layoutElements.add(e);
         }

      }

      public void addAttributeToBand(NewAttribute attribute, InputStream inputStream) {
         this.countElement.addAttributeToBand(attribute, inputStream);
         int count = this.countElement.latestValue();

         for(int i = 0; i < count; ++i) {
            for(AttributeLayoutElement layoutElement : this.layoutElements) {
               layoutElement.addAttributeToBand(attribute, inputStream);
            }
         }

      }

      public Integral getCountElement() {
         return this.countElement;
      }

      public List getLayoutElements() {
         return this.layoutElements;
      }

      public void pack(OutputStream out) throws IOException, Pack200Exception {
         this.countElement.pack(out);

         for(AttributeLayoutElement layoutElement : this.layoutElements) {
            layoutElement.pack(out);
         }

      }

      public void renumberBci(IntList bciRenumbering, Map labelsToOffsets) {
         for(AttributeLayoutElement layoutElement : this.layoutElements) {
            layoutElement.renumberBci(bciRenumbering, labelsToOffsets);
         }

      }
   }

   public class Union extends LayoutElement {
      private final Integral unionTag;
      private final List unionCases;
      private final List defaultCaseBody;

      public Union(String tag, List unionCases, List body) {
         this.unionTag = NewAttributeBands.this.new Integral(tag);
         this.unionCases = unionCases;
         this.defaultCaseBody = body;
      }

      public void addAttributeToBand(NewAttribute attribute, InputStream inputStream) {
         this.unionTag.addAttributeToBand(attribute, inputStream);
         long tag = (long)this.unionTag.latestValue();
         boolean defaultCase = true;

         for(UnionCase unionCase : this.unionCases) {
            if (unionCase.hasTag(tag)) {
               defaultCase = false;
               unionCase.addAttributeToBand(attribute, inputStream);
            }
         }

         if (defaultCase) {
            for(LayoutElement layoutElement : this.defaultCaseBody) {
               layoutElement.addAttributeToBand(attribute, inputStream);
            }
         }

      }

      public List getDefaultCaseBody() {
         return this.defaultCaseBody;
      }

      public List getUnionCases() {
         return this.unionCases;
      }

      public Integral getUnionTag() {
         return this.unionTag;
      }

      public void pack(OutputStream outputStream) throws IOException, Pack200Exception {
         this.unionTag.pack(outputStream);

         for(UnionCase unionCase : this.unionCases) {
            unionCase.pack(outputStream);
         }

         for(LayoutElement element : this.defaultCaseBody) {
            element.pack(outputStream);
         }

      }

      public void renumberBci(IntList bciRenumbering, Map labelsToOffsets) {
         for(UnionCase unionCase : this.unionCases) {
            unionCase.renumberBci(bciRenumbering, labelsToOffsets);
         }

         for(LayoutElement element : this.defaultCaseBody) {
            element.renumberBci(bciRenumbering, labelsToOffsets);
         }

      }
   }

   public class UnionCase extends LayoutElement {
      private final List body;
      private final List tags;

      public UnionCase(List tags) {
         this.tags = tags;
         this.body = Collections.EMPTY_LIST;
      }

      public UnionCase(List tags, List body) {
         this.tags = tags;
         this.body = body;
      }

      public void addAttributeToBand(NewAttribute attribute, InputStream inputStream) {
         for(LayoutElement element : this.body) {
            element.addAttributeToBand(attribute, inputStream);
         }

      }

      public List getBody() {
         return this.body;
      }

      public boolean hasTag(long l) {
         return this.tags.contains((int)l);
      }

      public void pack(OutputStream outputStream) throws IOException, Pack200Exception {
         for(LayoutElement element : this.body) {
            element.pack(outputStream);
         }

      }

      public void renumberBci(IntList bciRenumbering, Map labelsToOffsets) {
         for(LayoutElement element : this.body) {
            element.renumberBci(bciRenumbering, labelsToOffsets);
         }

      }
   }

   public interface AttributeLayoutElement {
      void addAttributeToBand(NewAttribute var1, InputStream var2);

      void pack(OutputStream var1) throws IOException, Pack200Exception;

      void renumberBci(IntList var1, Map var2);
   }
}
