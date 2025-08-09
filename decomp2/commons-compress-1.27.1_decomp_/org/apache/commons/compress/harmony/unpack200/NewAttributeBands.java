package org.apache.commons.compress.harmony.unpack200;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.compress.harmony.pack200.BHSDCodec;
import org.apache.commons.compress.harmony.pack200.Codec;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.Attribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPClass;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPDouble;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPFieldRef;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPFloat;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPInteger;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPInterfaceMethodRef;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPLong;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPMethodRef;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPNameAndType;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPString;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8;
import org.apache.commons.compress.harmony.unpack200.bytecode.NewAttribute;
import org.apache.commons.compress.utils.ParsingUtils;

public class NewAttributeBands extends BandSet {
   private final AttributeLayout attributeLayout;
   private int backwardsCallCount;
   protected List attributeLayoutElements;

   public NewAttributeBands(Segment segment, AttributeLayout attributeLayout) throws IOException {
      super(segment);
      this.attributeLayout = attributeLayout;
      this.parseLayout();
      attributeLayout.setBackwardsCallCount(this.backwardsCallCount);
   }

   public int getBackwardsCallCount() {
      return this.backwardsCallCount;
   }

   public BHSDCodec getCodec(String layoutElement) {
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

   private Attribute getOneAttribute(int index, List elements) {
      NewAttribute attribute = new NewAttribute(this.segment.getCpBands().cpUTF8Value(this.attributeLayout.getName()), this.attributeLayout.getIndex());

      for(AttributeLayoutElement element : elements) {
         element.addToAttribute(index, attribute);
      }

      return attribute;
   }

   private StringReader getStreamUpToMatchingBracket(StringReader stream) throws IOException {
      StringBuilder sb = new StringBuilder();
      int foundBracket = -1;

      while(foundBracket != 0) {
         int read = stream.read();
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

   public List parseAttributes(InputStream in, int occurrenceCount) throws IOException, Pack200Exception {
      for(AttributeLayoutElement element : this.attributeLayoutElements) {
         element.readBands(in, occurrenceCount);
      }

      List<Attribute> attributes = new ArrayList(occurrenceCount);

      for(int i = 0; i < occurrenceCount; ++i) {
         attributes.add(this.getOneAttribute(i, this.attributeLayoutElements));
      }

      return attributes;
   }

   private void parseLayout() throws IOException {
      if (this.attributeLayoutElements == null) {
         this.attributeLayoutElements = new ArrayList();
         StringReader stream = new StringReader(this.attributeLayout.getLayout());

         AttributeLayoutElement e;
         while((e = this.readNextAttributeElement(stream)) != null) {
            this.attributeLayoutElements.add(e);
         }

         this.resolveCalls();
      }

   }

   public void read(InputStream in) throws IOException, Pack200Exception {
   }

   private List readBody(StringReader stream) throws IOException {
      List<LayoutElement> layoutElements = new ArrayList();

      LayoutElement e;
      while((e = this.readNextLayoutElement(stream)) != null) {
         layoutElements.add(e);
      }

      return layoutElements;
   }

   private AttributeLayoutElement readNextAttributeElement(StringReader stream) throws IOException {
      stream.mark(1);
      int next = stream.read();
      if (next == -1) {
         return null;
      } else if (next == 91) {
         return new Callable(this.readBody(this.getStreamUpToMatchingBracket(stream)));
      } else {
         stream.reset();
         return this.readNextLayoutElement(stream);
      }
   }

   private LayoutElement readNextLayoutElement(StringReader stream) throws IOException {
      int nextChar = stream.read();
      if (nextChar == -1) {
         return null;
      } else {
         switch (nextChar) {
            case 40:
               int number = this.readNumber(stream);
               stream.read();
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
               return new Integral(new String(new char[]{(char)nextChar, (char)stream.read()}));
            case 75:
            case 82:
               StringBuilder string = (new StringBuilder("")).append((char)nextChar).append((char)stream.read());
               char nxt = (char)stream.read();
               string.append(nxt);
               if (nxt == 'N') {
                  string.append((char)stream.read());
               }

               return new Reference(string.toString());
            case 78:
               char uintType = (char)stream.read();
               stream.read();
               String str = this.readUpToMatchingBracket(stream);
               return new Replication("" + uintType, str);
            case 79:
               stream.mark(1);
               if (stream.read() != 83) {
                  stream.reset();
                  return new Integral("O" + (char)stream.read());
               }

               return new Integral("OS" + (char)stream.read());
            case 80:
               stream.mark(1);
               if (stream.read() != 79) {
                  stream.reset();
                  return new Integral("P" + (char)stream.read());
               }

               return new Integral("PO" + (char)stream.read());
            case 84:
               String intType = "" + (char)stream.read();
               if (intType.equals("S")) {
                  intType = intType + (char)stream.read();
               }

               List<UnionCase> unionCases = new ArrayList();

               UnionCase c;
               while((c = this.readNextUnionCase(stream)) != null) {
                  unionCases.add(c);
               }

               stream.read();
               stream.read();
               stream.read();
               List<LayoutElement> body = null;
               stream.mark(1);
               char next = (char)stream.read();
               if (next != ']') {
                  stream.reset();
                  body = this.readBody(this.getStreamUpToMatchingBracket(stream));
               }

               return new Union(intType, unionCases, body);
         }
      }
   }

   private UnionCase readNextUnionCase(StringReader stream) throws IOException {
      stream.mark(2);
      stream.read();
      int next = stream.read();
      char ch = (char)next;
      if (ch != ')' && next != -1) {
         stream.reset();
         stream.read();
         List<Integer> tags = new ArrayList();

         Integer nextTag;
         do {
            nextTag = this.readNumber(stream);
            if (nextTag != null) {
               tags.add(nextTag);
               stream.read();
            }
         } while(nextTag != null);

         stream.read();
         stream.mark(1);
         ch = (char)stream.read();
         if (ch == ']') {
            return new UnionCase(tags);
         } else {
            stream.reset();
            return new UnionCase(tags, this.readBody(this.getStreamUpToMatchingBracket(stream)));
         }
      } else {
         stream.reset();
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

   private String readUpToMatchingBracket(StringReader stream) throws IOException {
      StringBuilder sb = new StringBuilder();
      int foundBracket = -1;

      while(foundBracket != 0) {
         int read = stream.read();
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

   private void resolveCalls() {
      int backwardsCalls = 0;

      for(int i = 0; i < this.attributeLayoutElements.size(); ++i) {
         AttributeLayoutElement element = (AttributeLayoutElement)this.attributeLayoutElements.get(i);
         if (element instanceof Callable) {
            Callable callable = (Callable)element;
            if (i == 0) {
               callable.setFirstCallable(true);
            }

            for(LayoutElement layoutElement : callable.body) {
               backwardsCalls += this.resolveCallsForElement(i, callable, layoutElement);
            }
         }
      }

      this.backwardsCallCount = backwardsCalls;
   }

   private int resolveCallsForElement(int i, Callable currentCallable, LayoutElement layoutElement) {
      int backwardsCalls = 0;
      if (layoutElement instanceof Call) {
         Call call = (Call)layoutElement;
         int index = call.callableIndex;
         if (index == 0) {
            ++backwardsCalls;
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
            ++backwardsCalls;

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
            backwardsCalls += this.resolveCallsForElement(i, currentCallable, child);
         }
      }

      return backwardsCalls;
   }

   public void setBackwardsCalls(int[] backwardsCalls) throws IOException {
      int index = 0;
      this.parseLayout();

      for(AttributeLayoutElement element : this.attributeLayoutElements) {
         if (element instanceof Callable && ((Callable)element).isBackwardsCallable()) {
            ((Callable)element).addCount(backwardsCalls[index]);
            ++index;
         }
      }

   }

   public void unpack() throws IOException, Pack200Exception {
   }

   public class Call extends LayoutElement {
      private final int callableIndex;
      private Callable callable;

      public Call(int callableIndex) {
         this.callableIndex = callableIndex;
      }

      public void addToAttribute(int n, NewAttribute attribute) {
         this.callable.addNextToAttribute(attribute);
      }

      public Callable getCallable() {
         return this.callable;
      }

      public int getCallableIndex() {
         return this.callableIndex;
      }

      public void readBands(InputStream in, int count) {
         if (this.callableIndex > 0) {
            this.callable.addCount(count);
         }

      }

      public void setCallable(Callable callable) {
         this.callable = callable;
         if (this.callableIndex < 1) {
            callable.setBackwardsCallable();
         }

      }
   }

   public static class Callable implements AttributeLayoutElement {
      private final List body;
      private boolean isBackwardsCallable;
      private boolean isFirstCallable;
      private int count;
      private int index;

      public Callable(List body) {
         this.body = body;
      }

      public void addCount(int count) {
         this.count += count;
      }

      public void addNextToAttribute(NewAttribute attribute) {
         for(LayoutElement element : this.body) {
            element.addToAttribute(this.index, attribute);
         }

         ++this.index;
      }

      public void addToAttribute(int n, NewAttribute attribute) {
         if (this.isFirstCallable) {
            for(LayoutElement element : this.body) {
               element.addToAttribute(this.index, attribute);
            }

            ++this.index;
         }

      }

      public List getBody() {
         return this.body;
      }

      public boolean isBackwardsCallable() {
         return this.isBackwardsCallable;
      }

      public void readBands(InputStream in, int count) throws IOException, Pack200Exception {
         if (this.isFirstCallable) {
            count += this.count;
         } else {
            count = this.count;
         }

         for(LayoutElement element : this.body) {
            element.readBands(in, count);
         }

      }

      public void setBackwardsCallable() {
         this.isBackwardsCallable = true;
      }

      public void setFirstCallable(boolean isFirstCallable) {
         this.isFirstCallable = isFirstCallable;
      }
   }

   public class Integral extends LayoutElement {
      private final String tag;
      private int[] band;

      public Integral(String tag) {
         this.tag = tag;
      }

      public void addToAttribute(int n, NewAttribute attribute) {
         int value = this.band[n];
         if (!this.tag.equals("B") && !this.tag.equals("FB")) {
            if (this.tag.equals("SB")) {
               attribute.addInteger(1, (long)((byte)value));
            } else if (!this.tag.equals("H") && !this.tag.equals("FH")) {
               if (this.tag.equals("SH")) {
                  attribute.addInteger(2, (long)((short)value));
               } else if (!this.tag.equals("I") && !this.tag.equals("FI") && !this.tag.equals("SI")) {
                  if (!this.tag.equals("V") && !this.tag.equals("FV") && !this.tag.equals("SV")) {
                     if (this.tag.startsWith("PO")) {
                        char uintType = this.tag.substring(2).toCharArray()[0];
                        int length = this.getLength(uintType);
                        attribute.addBCOffset(length, value);
                     } else if (this.tag.startsWith("P")) {
                        char uintType = this.tag.substring(1).toCharArray()[0];
                        int length = this.getLength(uintType);
                        attribute.addBCIndex(length, value);
                     } else if (this.tag.startsWith("OS")) {
                        char uintType = this.tag.substring(2).toCharArray()[0];
                        int length = this.getLength(uintType);
                        switch (length) {
                           case 1:
                              value = (byte)value;
                              break;
                           case 2:
                              value = (short)value;
                           case 3:
                           default:
                              break;
                           case 4:
                              value = value;
                        }

                        attribute.addBCLength(length, value);
                     } else if (this.tag.startsWith("O")) {
                        char uintType = this.tag.substring(1).toCharArray()[0];
                        int length = this.getLength(uintType);
                        attribute.addBCLength(length, value);
                     }
                  }
               } else {
                  attribute.addInteger(4, (long)value);
               }
            } else {
               attribute.addInteger(2, (long)value);
            }
         } else {
            attribute.addInteger(1, (long)value);
         }

      }

      public String getTag() {
         return this.tag;
      }

      int getValue(int index) {
         return this.band[index];
      }

      public void readBands(InputStream in, int count) throws IOException, Pack200Exception {
         this.band = NewAttributeBands.this.decodeBandInt(NewAttributeBands.this.attributeLayout.getName() + "_" + this.tag, in, NewAttributeBands.this.getCodec(this.tag), count);
      }
   }

   private abstract static class LayoutElement implements AttributeLayoutElement {
      private LayoutElement() {
      }

      protected int getLength(char uintType) {
         int length = 0;
         switch (uintType) {
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
      private Object band;
      private final int length;

      public Reference(String tag) {
         this.tag = tag;
         this.length = this.getLength(tag.charAt(tag.length() - 1));
      }

      public void addToAttribute(int n, NewAttribute attribute) {
         if (this.tag.startsWith("KI")) {
            attribute.addToBody(this.length, ((CPInteger[])this.band)[n]);
         } else if (this.tag.startsWith("KJ")) {
            attribute.addToBody(this.length, ((CPLong[])this.band)[n]);
         } else if (this.tag.startsWith("KF")) {
            attribute.addToBody(this.length, ((CPFloat[])this.band)[n]);
         } else if (this.tag.startsWith("KD")) {
            attribute.addToBody(this.length, ((CPDouble[])this.band)[n]);
         } else if (this.tag.startsWith("KS")) {
            attribute.addToBody(this.length, ((CPString[])this.band)[n]);
         } else if (this.tag.startsWith("RC")) {
            attribute.addToBody(this.length, ((CPClass[])this.band)[n]);
         } else if (this.tag.startsWith("RS")) {
            attribute.addToBody(this.length, ((CPUTF8[])this.band)[n]);
         } else if (this.tag.startsWith("RD")) {
            attribute.addToBody(this.length, ((CPNameAndType[])this.band)[n]);
         } else if (this.tag.startsWith("RF")) {
            attribute.addToBody(this.length, ((CPFieldRef[])this.band)[n]);
         } else if (this.tag.startsWith("RM")) {
            attribute.addToBody(this.length, ((CPMethodRef[])this.band)[n]);
         } else if (this.tag.startsWith("RI")) {
            attribute.addToBody(this.length, ((CPInterfaceMethodRef[])this.band)[n]);
         } else if (this.tag.startsWith("RU")) {
            attribute.addToBody(this.length, ((CPUTF8[])this.band)[n]);
         }

      }

      public String getTag() {
         return this.tag;
      }

      public void readBands(InputStream in, int count) throws IOException, Pack200Exception {
         if (this.tag.startsWith("KI")) {
            this.band = NewAttributeBands.this.parseCPIntReferences(NewAttributeBands.this.attributeLayout.getName(), in, Codec.UNSIGNED5, count);
         } else if (this.tag.startsWith("KJ")) {
            this.band = NewAttributeBands.this.parseCPLongReferences(NewAttributeBands.this.attributeLayout.getName(), in, Codec.UNSIGNED5, count);
         } else if (this.tag.startsWith("KF")) {
            this.band = NewAttributeBands.this.parseCPFloatReferences(NewAttributeBands.this.attributeLayout.getName(), in, Codec.UNSIGNED5, count);
         } else if (this.tag.startsWith("KD")) {
            this.band = NewAttributeBands.this.parseCPDoubleReferences(NewAttributeBands.this.attributeLayout.getName(), in, Codec.UNSIGNED5, count);
         } else if (this.tag.startsWith("KS")) {
            this.band = NewAttributeBands.this.parseCPStringReferences(NewAttributeBands.this.attributeLayout.getName(), in, Codec.UNSIGNED5, count);
         } else if (this.tag.startsWith("RC")) {
            this.band = NewAttributeBands.this.parseCPClassReferences(NewAttributeBands.this.attributeLayout.getName(), in, Codec.UNSIGNED5, count);
         } else if (this.tag.startsWith("RS")) {
            this.band = NewAttributeBands.this.parseCPSignatureReferences(NewAttributeBands.this.attributeLayout.getName(), in, Codec.UNSIGNED5, count);
         } else if (this.tag.startsWith("RD")) {
            this.band = NewAttributeBands.this.parseCPDescriptorReferences(NewAttributeBands.this.attributeLayout.getName(), in, Codec.UNSIGNED5, count);
         } else if (this.tag.startsWith("RF")) {
            this.band = NewAttributeBands.this.parseCPFieldRefReferences(NewAttributeBands.this.attributeLayout.getName(), in, Codec.UNSIGNED5, count);
         } else if (this.tag.startsWith("RM")) {
            this.band = NewAttributeBands.this.parseCPMethodRefReferences(NewAttributeBands.this.attributeLayout.getName(), in, Codec.UNSIGNED5, count);
         } else if (this.tag.startsWith("RI")) {
            this.band = NewAttributeBands.this.parseCPInterfaceMethodRefReferences(NewAttributeBands.this.attributeLayout.getName(), in, Codec.UNSIGNED5, count);
         } else if (this.tag.startsWith("RU")) {
            this.band = NewAttributeBands.this.parseCPUTF8References(NewAttributeBands.this.attributeLayout.getName(), in, Codec.UNSIGNED5, count);
         }

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

      public void addToAttribute(int index, NewAttribute attribute) {
         this.countElement.addToAttribute(index, attribute);
         int offset = 0;

         for(int i = 0; i < index; ++i) {
            offset += this.countElement.getValue(i);
         }

         long numElements = (long)this.countElement.getValue(index);

         for(int i = offset; (long)i < (long)offset + numElements; ++i) {
            for(LayoutElement layoutElement : this.layoutElements) {
               layoutElement.addToAttribute(i, attribute);
            }
         }

      }

      public Integral getCountElement() {
         return this.countElement;
      }

      public List getLayoutElements() {
         return this.layoutElements;
      }

      public void readBands(InputStream in, int count) throws IOException, Pack200Exception {
         this.countElement.readBands(in, count);
         int arrayCount = 0;

         for(int i = 0; i < count; ++i) {
            arrayCount += this.countElement.getValue(i);
         }

         for(LayoutElement layoutElement : this.layoutElements) {
            layoutElement.readBands(in, arrayCount);
         }

      }
   }

   public class Union extends LayoutElement {
      private final Integral unionTag;
      private final List unionCases;
      private final List defaultCaseBody;
      private int[] caseCounts;
      private int defaultCount;

      public Union(String tag, List unionCases, List body) {
         this.unionTag = NewAttributeBands.this.new Integral(tag);
         this.unionCases = unionCases;
         this.defaultCaseBody = body;
      }

      public void addToAttribute(int n, NewAttribute attribute) {
         this.unionTag.addToAttribute(n, attribute);
         int offset = 0;
         int[] tagBand = this.unionTag.band;
         int tag = this.unionTag.getValue(n);
         boolean defaultCase = true;

         for(UnionCase unionCase : this.unionCases) {
            if (unionCase.hasTag(tag)) {
               defaultCase = false;

               for(int j = 0; j < n; ++j) {
                  if (unionCase.hasTag(tagBand[j])) {
                     ++offset;
                  }
               }

               unionCase.addToAttribute(offset, attribute);
            }
         }

         if (defaultCase) {
            int defaultOffset = 0;

            for(int j = 0; j < n; ++j) {
               boolean found = false;

               for(UnionCase unionCase : this.unionCases) {
                  if (unionCase.hasTag(tagBand[j])) {
                     found = true;
                  }
               }

               if (!found) {
                  ++defaultOffset;
               }
            }

            if (this.defaultCaseBody != null) {
               for(LayoutElement element : this.defaultCaseBody) {
                  element.addToAttribute(defaultOffset, attribute);
               }
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

      public void readBands(InputStream in, int count) throws IOException, Pack200Exception {
         this.unionTag.readBands(in, count);
         int[] values = this.unionTag.band;
         this.caseCounts = new int[this.unionCases.size()];

         for(int i = 0; i < this.caseCounts.length; ++i) {
            UnionCase unionCase = (UnionCase)this.unionCases.get(i);

            for(int value : values) {
               if (unionCase.hasTag(value)) {
                  int var10002 = this.caseCounts[i]++;
               }
            }

            unionCase.readBands(in, this.caseCounts[i]);
         }

         for(int value : values) {
            boolean found = false;

            for(UnionCase unionCase : this.unionCases) {
               if (unionCase.hasTag(value)) {
                  found = true;
               }
            }

            if (!found) {
               ++this.defaultCount;
            }
         }

         if (this.defaultCaseBody != null) {
            for(LayoutElement element : this.defaultCaseBody) {
               element.readBands(in, this.defaultCount);
            }
         }

      }
   }

   public class UnionCase extends LayoutElement {
      private List body;
      private final List tags;

      public UnionCase(List tags) {
         this.tags = tags;
      }

      public UnionCase(List tags, List body) {
         this.tags = tags;
         this.body = body;
      }

      public void addToAttribute(int index, NewAttribute attribute) {
         if (this.body != null) {
            for(LayoutElement element : this.body) {
               element.addToAttribute(index, attribute);
            }
         }

      }

      public List getBody() {
         return this.body == null ? Collections.EMPTY_LIST : this.body;
      }

      public boolean hasTag(int i) {
         return this.tags.contains(i);
      }

      public boolean hasTag(long l) {
         return this.tags.contains((int)l);
      }

      public void readBands(InputStream in, int count) throws IOException, Pack200Exception {
         if (this.body != null) {
            for(LayoutElement element : this.body) {
               element.readBands(in, count);
            }
         }

      }
   }

   private interface AttributeLayoutElement {
      void addToAttribute(int var1, NewAttribute var2);

      void readBands(InputStream var1, int var2) throws IOException, Pack200Exception;
   }
}
