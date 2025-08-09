package org.codehaus.janino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.codehaus.commons.compiler.InternalCompilerException;
import org.codehaus.commons.nullanalysis.Nullable;
import org.codehaus.janino.util.ClassFile;

public class CodeContext {
   private static final boolean SUPPRESS_STACK_MAP_TABLE = Boolean.getBoolean(CodeContext.class.getName() + ".suppressStackMapTable");
   private static final int INITIAL_SIZE = 128;
   private final ClassFile classFile;
   private int maxStack;
   private short maxLocals;
   private byte[] code;
   private final Offset beginning;
   private final Inserter end;
   private Inserter currentInserter;
   private final List exceptionTableEntries;
   private final List allLocalVars = new ArrayList();
   @Nullable
   private LocalScope currentLocalScope;
   private short nextLocalVariableSlot;
   private final List relocatables = new ArrayList();
   private static final Map BRANCH_OPCODE_INVERSION = createBranchOpcodeInversion();

   public CodeContext(ClassFile classFile) {
      this.classFile = classFile;
      this.maxStack = 0;
      this.maxLocals = 0;
      this.code = new byte[128];
      this.exceptionTableEntries = new ArrayList();
      this.beginning = new Offset();
      this.beginning.offset = 0;
      this.currentInserter = new Inserter();
      this.currentInserter.offset = 0;
      this.currentInserter.setStackMap(new StackMap(new ClassFile.StackMapTableAttribute.VerificationTypeInfo[0], new ClassFile.StackMapTableAttribute.VerificationTypeInfo[0]));
      this.beginning.next = this.currentInserter;
      this.currentInserter.prev = this.beginning;
      this.end = this.currentInserter;
   }

   public ClassFile getClassFile() {
      return this.classFile;
   }

   public short allocateLocalVariable(short size) {
      return this.allocateLocalVariable(size, (String)null, (IType)null).getSlotIndex();
   }

   public Java.LocalVariableSlot allocateLocalVariable(short size, @Nullable String name, @Nullable IType type) {
      LocalScope currentScope = this.currentLocalScope;

      assert currentScope != null : "saveLocalVariables must be called first";

      List<Java.LocalVariableSlot> currentVars = currentScope.localVars;
      Java.LocalVariableSlot slot = new Java.LocalVariableSlot(name, this.nextLocalVariableSlot, type);
      if (name != null) {
         slot.setStart(this.newOffset());
      }

      this.nextLocalVariableSlot += size;
      currentVars.add(slot);
      this.allLocalVars.add(slot);
      if (this.nextLocalVariableSlot > this.maxLocals) {
         this.maxLocals = this.nextLocalVariableSlot;
      }

      return slot;
   }

   public List saveLocalVariables() {
      return (this.currentLocalScope = new LocalScope(this.currentLocalScope, this.nextLocalVariableSlot, this.currentInserter.getStackMap())).localVars;
   }

   public void restoreLocalVariables() {
      LocalScope scopeToPop = this.currentLocalScope;

      assert scopeToPop != null;

      for(Java.LocalVariableSlot slot : scopeToPop.localVars) {
         if (slot.getName() != null) {
            slot.setEnd(this.newOffset());
         }

         this.allLocalVars.remove(slot);
      }

      this.currentLocalScope = scopeToPop.parent;
      this.nextLocalVariableSlot = scopeToPop.startingLocalVariableSlot;
      if (this.currentLocalScope != null) {
         StackMap sm = this.currentInserter.getStackMap();
         if (sm != null && sm.locals().length > 0) {
            int numActiveSlots = 0;
            int nextLvIndex = 0;

            for(ClassFile.StackMapTableAttribute.VerificationTypeInfo slot : sm.locals()) {
               if (nextLvIndex >= this.nextLocalVariableSlot) {
                  break;
               }

               nextLvIndex += slot.category();
               ++numActiveSlots;
            }

            for(int numRemovedSlots = sm.locals().length - numActiveSlots; numRemovedSlots-- > 0; sm = sm.popLocal()) {
            }

            this.currentInserter.setStackMap(sm);
         }
      }

   }

   public ClassFile.CodeAttribute newCodeAttribute(int initialLocalsCount, boolean debugLines, boolean debugVars) {
      ClassFile.CodeAttribute.ExceptionTableEntry[] etes = new ClassFile.CodeAttribute.ExceptionTableEntry[this.exceptionTableEntries.size()];

      for(int i = 0; i < etes.length; ++i) {
         ExceptionTableEntry ete = (ExceptionTableEntry)this.exceptionTableEntries.get(i);
         etes[i] = new ClassFile.CodeAttribute.ExceptionTableEntry((short)ete.startPc.offset, (short)ete.endPc.offset, (short)ete.handlerPc.offset, ete.catchType);
      }

      List<ClassFile.AttributeInfo> attributes = new ArrayList();
      if (debugLines) {
         attributes.add(this.newLineNumberTableAttribute());
      }

      if (debugVars) {
         ClassFile.LocalVariableTableAttribute lvta = this.newLocalVariableTableAttribute();
         if (lvta != null) {
            attributes.add(lvta);
         }
      }

      if (!SUPPRESS_STACK_MAP_TABLE) {
         ClassFile.StackMapTableAttribute smta = this.newStackMapTableAttribute(initialLocalsCount);
         if (smta != null) {
            attributes.add(smta);
         }
      }

      ClassFile.AttributeInfo[] aia = (ClassFile.AttributeInfo[])attributes.toArray(new ClassFile.AttributeInfo[attributes.size()]);
      return new ClassFile.CodeAttribute(this.classFile.addConstantUtf8Info("Code"), (short)this.maxStack, this.maxLocals, Arrays.copyOf(this.code, this.end.offset), etes, aia);
   }

   private ClassFile.LineNumberTableAttribute newLineNumberTableAttribute() {
      List<ClassFile.LineNumberTableAttribute.Entry> lnt = new ArrayList();

      for(Offset o = this.beginning; o != null; o = o.next) {
         if (o instanceof LineNumberOffset) {
            int offset = o.offset;
            if (offset > 65535) {
               throw new InternalCompilerException("LineNumberTable entry offset out of range");
            }

            short lineNumber = ((LineNumberOffset)o).lineNumber;
            lnt.add(new ClassFile.LineNumberTableAttribute.Entry((short)offset, lineNumber));
         }
      }

      ClassFile.LineNumberTableAttribute.Entry[] lnte = (ClassFile.LineNumberTableAttribute.Entry[])lnt.toArray(new ClassFile.LineNumberTableAttribute.Entry[lnt.size()]);
      return new ClassFile.LineNumberTableAttribute(this.classFile.addConstantUtf8Info("LineNumberTable"), lnte);
   }

   @Nullable
   private ClassFile.StackMapTableAttribute newStackMapTableAttribute(int initialLocalsCount) {
      Offset frame = this.beginning.next;

      for(Offset previousFrame = null; frame != this.end && frame.stackMap.locals().length < initialLocalsCount; frame = frame.next) {
      }

      Offset var14 = frame;
      frame = frame.next;

      List<ClassFile.StackMapTableAttribute.StackMapFrame> smfs;
      for(smfs = new ArrayList(); frame != null && frame.offset != this.end.offset; frame = frame.next) {
         if (frame instanceof BasicBlock) {
            for(Offset o = frame.next; o != null && o.offset == frame.offset; o = o.next) {
               if (o instanceof BasicBlock) {
                  frame = o;
               }
            }

            if (frame.getStackMap() != null) {
               int offsetDelta = smfs.isEmpty() ? frame.offset : frame.offset - var14.offset - 1;
               ClassFile.StackMapTableAttribute.VerificationTypeInfo[] frameOperands = frame.getStackMap().operands();
               int frameOperandsLength = frameOperands.length;
               ClassFile.StackMapTableAttribute.VerificationTypeInfo[] frameLocals = frame.getStackMap().locals();
               int frameLocalsLength = frameLocals.length;
               ClassFile.StackMapTableAttribute.VerificationTypeInfo[] previousFrameLocals = var14.getStackMap().locals();
               int previousFrameLocalsLength = previousFrameLocals.length;
               int k = 99;
               if (frameOperandsLength == 0 && Arrays.equals(frameLocals, previousFrameLocals)) {
                  if (offsetDelta <= 63) {
                     smfs.add(new ClassFile.StackMapTableAttribute.SameFrame(offsetDelta));
                  } else {
                     smfs.add(new ClassFile.StackMapTableAttribute.SameFrameExtended(offsetDelta));
                  }
               } else if (frameOperandsLength == 1 && Arrays.equals(frameLocals, previousFrameLocals)) {
                  if (offsetDelta <= 63) {
                     smfs.add(new ClassFile.StackMapTableAttribute.SameLocals1StackItemFrame(offsetDelta, frameOperands[0]));
                  } else {
                     smfs.add(new ClassFile.StackMapTableAttribute.SameLocals1StackItemFrameExtended(offsetDelta, frameOperands[0]));
                  }
               } else if (frameOperandsLength == 0 && (k = previousFrameLocalsLength - frameLocalsLength) >= 1 && k <= 3 && Arrays.equals(frameLocals, Arrays.copyOf(previousFrameLocals, frameLocalsLength))) {
                  smfs.add(new ClassFile.StackMapTableAttribute.ChopFrame(offsetDelta, k));
               } else if (frameOperandsLength == 0 && (k = frameLocalsLength - previousFrameLocalsLength) >= 1 && k <= 3 && Arrays.equals(previousFrameLocals, Arrays.copyOf(frameLocals, previousFrameLocalsLength))) {
                  smfs.add(new ClassFile.StackMapTableAttribute.AppendFrame(offsetDelta, (ClassFile.StackMapTableAttribute.VerificationTypeInfo[])Arrays.copyOfRange(frameLocals, previousFrameLocalsLength, frameLocalsLength)));
               } else {
                  smfs.add(new ClassFile.StackMapTableAttribute.FullFrame(offsetDelta, frameLocals, frameOperands));
               }

               var14 = frame;
            }
         }
      }

      return smfs.isEmpty() ? null : new ClassFile.StackMapTableAttribute(this.classFile.addConstantUtf8Info("StackMapTable"), (ClassFile.StackMapTableAttribute.StackMapFrame[])smfs.toArray(new ClassFile.StackMapTableAttribute.StackMapFrame[smfs.size()]));
   }

   private static IClass rawTypeOf(IType iType) {
      while(iType instanceof IParameterizedType) {
         iType = ((IParameterizedType)iType).getRawType();
      }

      assert iType instanceof IClass;

      return (IClass)iType;
   }

   @Nullable
   protected ClassFile.LocalVariableTableAttribute newLocalVariableTableAttribute() {
      List<ClassFile.LocalVariableTableAttribute.Entry> entryList = new ArrayList();

      for(Java.LocalVariableSlot slot : this.getAllLocalVars()) {
         String localVariableName = slot.getName();
         if (localVariableName != null) {
            String typeName = rawTypeOf(slot.getType()).getDescriptor();
            short classSlot = this.classFile.addConstantUtf8Info(typeName);
            short varNameSlot = this.classFile.addConstantUtf8Info(localVariableName);
            Offset start = slot.getStart();
            Offset end2 = slot.getEnd();

            assert start != null;

            assert end2 != null;

            entryList.add(new ClassFile.LocalVariableTableAttribute.Entry((short)start.offset, (short)(end2.offset - start.offset), varNameSlot, classSlot, slot.getSlotIndex()));
         }
      }

      if (entryList.isEmpty()) {
         return null;
      } else {
         return new ClassFile.LocalVariableTableAttribute(this.classFile.addConstantUtf8Info("LocalVariableTable"), (ClassFile.LocalVariableTableAttribute.Entry[])entryList.toArray(new ClassFile.LocalVariableTableAttribute.Entry[entryList.size()]));
      }
   }

   public void fixUpAndRelocate() {
      this.maybeGrow();
      this.fixUp();
      this.relocate();
   }

   private void maybeGrow() {
      for(int i = 0; i < this.relocatables.size(); ++i) {
         ((Relocatable)this.relocatables.get(i)).grow();
      }

   }

   private void fixUp() {
      for(Offset o = this.beginning; o != this.end; o = o.next) {
         assert o != null;

         if (o instanceof FixUp) {
            ((FixUp)o).fixUp();
         }
      }

   }

   private void relocate() {
      for(Relocatable relocatable : this.relocatables) {
         relocatable.relocate();
      }

   }

   public void write(byte[] b) {
      if (b.length != 0) {
         int o = this.makeSpace(b.length);
         System.arraycopy(b, 0, this.code, o, b.length);
      }
   }

   public void write(byte b1) {
      int o = this.makeSpace(1);
      this.code[o] = b1;
   }

   public void write(byte b1, byte b2) {
      int o = this.makeSpace(2);
      this.code[o++] = b1;
      this.code[o] = b2;
   }

   public void write(byte b1, byte b2, byte b3) {
      int o = this.makeSpace(3);
      this.code[o++] = b1;
      this.code[o++] = b2;
      this.code[o] = b3;
   }

   public void write(byte b1, byte b2, byte b3, byte b4) {
      int o = this.makeSpace(4);
      this.code[o++] = b1;
      this.code[o++] = b2;
      this.code[o++] = b3;
      this.code[o] = b4;
   }

   public void write(byte b1, byte b2, byte b3, byte b4, byte b5) {
      int o = this.makeSpace(5);
      this.code[o++] = b1;
      this.code[o++] = b2;
      this.code[o++] = b3;
      this.code[o++] = b4;
      this.code[o] = b5;
   }

   public void addLineNumberOffset(int lineNumber) {
      if (lineNumber != -1) {
         if (lineNumber > 65535) {
            lineNumber = 65535;
         }

         for(Offset o = this.currentInserter.prev; o != this.beginning; o = o.prev) {
            assert o != null;

            if (o instanceof LineNumberOffset) {
               if ((((LineNumberOffset)o).lineNumber & '\uffff') == lineNumber) {
                  return;
               }
               break;
            }
         }

         LineNumberOffset lno = new LineNumberOffset(this.currentInserter.offset, this.currentInserter.getStackMap(), (short)lineNumber);
         Offset cip = this.currentInserter.prev;

         assert cip != null;

         lno.prev = cip;
         lno.next = this.currentInserter;
         cip.next = lno;
         this.currentInserter.prev = lno;
      }
   }

   public int makeSpace(int size) {
      int cio = this.currentInserter.offset;
      if (size != 0) {
         if (size < 0) {
            assert cio <= this.end.offset + size;

            System.arraycopy(this.code, cio - size, this.code, cio, this.end.offset - cio + size);

            for(Offset o = this.currentInserter.next; o != null; o = o.next) {
               o.offset += size;
            }
         } else if (this.end.offset + size <= this.code.length) {
            if (cio != this.end.offset) {
               System.arraycopy(this.code, cio, this.code, cio + size, this.end.offset - cio);
               Arrays.fill(this.code, cio, cio + size, (byte)0);
            }

            for(Offset o = this.currentInserter; o != null; o = o.next) {
               o.offset += size;
            }
         } else {
            byte[] oldCode = this.code;
            int newSize = Math.max(Math.min(oldCode.length * 2, 65535), oldCode.length + size);
            if (newSize > 65535) {
               throw new InternalCompilerException("Code grows beyond 64 KB");
            }

            this.code = new byte[newSize];
            System.arraycopy(oldCode, 0, this.code, 0, cio);
            System.arraycopy(oldCode, cio, this.code, cio + size, this.end.offset - cio);
            Arrays.fill(this.code, cio, cio + size, (byte)0);

            for(Offset o = this.currentInserter; o != null; o = o.next) {
               o.offset += size;
            }
         }
      }

      return cio;
   }

   public void writeShort(int v) {
      this.write((byte)(v >> 8), (byte)v);
   }

   public void writeBranch(int opcode, Offset dst) {
      assert dst instanceof BasicBlock;

      if (dst.offset == -1 && dst.stackMap == null) {
         dst.stackMap = this.currentInserter.getStackMap();
      }

      int opcodeJsr = 168;
      if ((opcode < 153 || opcode > opcodeJsr) && (opcode < 198 || opcode > 199)) {
         if (opcode < 200 || opcode > 201) {
            throw new AssertionError(opcode);
         }

         this.relocatables.add(new Branch(opcode, dst));
         this.write((byte)opcode, (byte)-1, (byte)-1, (byte)-1, (byte)-1);
      } else {
         this.relocatables.add(new Branch(opcode, dst));
         this.write((byte)opcode, (byte)-1, (byte)-1);
      }

   }

   private static int invertBranchOpcode(int branchOpcode) {
      Integer result = (Integer)BRANCH_OPCODE_INVERSION.get(branchOpcode);

      assert result != null : branchOpcode;

      return result;
   }

   private static Map createBranchOpcodeInversion() {
      Map<Integer, Integer> m = new HashMap();
      m.put(165, 166);
      m.put(166, 165);
      m.put(159, 160);
      m.put(160, 159);
      m.put(162, 161);
      m.put(161, 162);
      m.put(163, 164);
      m.put(164, 163);
      m.put(153, 154);
      m.put(154, 153);
      m.put(156, 155);
      m.put(155, 156);
      m.put(157, 158);
      m.put(158, 157);
      m.put(198, 199);
      m.put(199, 198);
      return Collections.unmodifiableMap(m);
   }

   public void writeOffset(Offset src, Offset dst) {
      Offset o = new FourByteOffset();
      o.set();
      this.relocatables.add(new OffsetBranch(o, src, dst));
      this.makeSpace(4);
   }

   public Offset newOffset() {
      Offset o = new Offset();
      o.set();
      return o;
   }

   public Offset newBasicBlock() {
      Offset o = new BasicBlock();
      o.set();
      return o;
   }

   public Inserter newInserter() {
      Inserter i = new Inserter();
      i.set();
      return i;
   }

   public Inserter currentInserter() {
      return this.currentInserter;
   }

   public void pushInserter(Inserter ins) {
      ins.getStackMap();
      if (ins.nextInserter != null) {
         throw new InternalCompilerException("An Inserter can only be pushed once at a time");
      } else {
         ins.nextInserter = this.currentInserter;
         this.currentInserter = ins;
      }
   }

   public void popInserter() {
      Inserter ni = this.currentInserter.nextInserter;
      if (ni == null) {
         throw new InternalCompilerException("Code inserter stack underflow");
      } else {
         ni.getStackMap();
         this.currentInserter.nextInserter = null;
         this.currentInserter = ni;
      }
   }

   @Nullable
   private static final StackMap mergeStackMaps(@Nullable StackMap sm1, @Nullable StackMap sm2) {
      if (sm1 == null) {
         return sm2;
      } else if (sm2 == null) {
         return sm1;
      } else if (sm1 == sm2) {
         return sm1;
      } else if (sm1.equals(sm2)) {
         return sm1;
      } else if (!Arrays.equals(sm1.operands(), sm2.operands())) {
         throw new InternalCompilerException("Inconsistent operand stack: " + sm1 + " vs. " + sm2);
      } else {
         ClassFile.StackMapTableAttribute.VerificationTypeInfo[] locals1 = sm1.locals();
         ClassFile.StackMapTableAttribute.VerificationTypeInfo[] locals2 = sm2.locals();
         List<ClassFile.StackMapTableAttribute.VerificationTypeInfo> tmp = new ArrayList();
         int i1 = 0;
         int i2 = 0;

         while(i1 < locals1.length && i2 < locals2.length) {
            ClassFile.StackMapTableAttribute.VerificationTypeInfo local1 = locals1[i1++];
            ClassFile.StackMapTableAttribute.VerificationTypeInfo local2 = locals2[i2++];
            if (local1.equals(local2)) {
               tmp.add(local1);
            } else {
               if (local1 == ClassFile.StackMapTableAttribute.TOP_VARIABLE_INFO && local2.category() == 2) {
                  assert i1 < locals1.length;

                  assert locals1[i1] == ClassFile.StackMapTableAttribute.TOP_VARIABLE_INFO;

                  ++i1;
                  tmp.add(ClassFile.StackMapTableAttribute.TOP_VARIABLE_INFO);
               } else if (local2 == ClassFile.StackMapTableAttribute.TOP_VARIABLE_INFO && local1.category() == 2) {
                  assert i2 < locals2.length;

                  assert locals2[i2] == ClassFile.StackMapTableAttribute.TOP_VARIABLE_INFO;

                  ++i2;
                  tmp.add(ClassFile.StackMapTableAttribute.TOP_VARIABLE_INFO);
               }

               tmp.add(ClassFile.StackMapTableAttribute.TOP_VARIABLE_INFO);
            }
         }

         return new StackMap((ClassFile.StackMapTableAttribute.VerificationTypeInfo[])tmp.toArray(new ClassFile.StackMapTableAttribute.VerificationTypeInfo[tmp.size()]), sm1.operands());
      }
   }

   public void addExceptionTableEntry(Offset startPc, Offset endPc, Offset handlerPc, @Nullable String catchTypeFd) {
      this.exceptionTableEntries.add(new ExceptionTableEntry(startPc, endPc, handlerPc, catchTypeFd == null ? 0 : this.classFile.addConstantClassInfo(catchTypeFd)));
   }

   public List getAllLocalVars() {
      return this.allLocalVars;
   }

   public void removeCode(Offset from, Offset to) {
      if (from != to) {
         int size = to.offset - from.offset;

         assert size >= 0;

         if (size != 0) {
            System.arraycopy(this.code, to.offset, this.code, from.offset, this.end.offset - to.offset);
            Set<Offset> invalidOffsets = new HashSet();
            Offset o = from.next;

            assert o != null;

            while(o != to) {
               assert o != null;

               invalidOffsets.add(o);
               Offset n = o.next;
               o.offset = -77;
               o.prev = null;
               o.next = null;
               o = n;

               assert n != null;
            }

            do {
               o.offset -= size;
               if (o == this.end) {
                  Iterator<Relocatable> it = this.relocatables.iterator();

                  while(it.hasNext()) {
                     Relocatable r = (Relocatable)it.next();
                     if (r instanceof Branch) {
                        Branch b = (Branch)r;
                        if (invalidOffsets.contains(b.source)) {
                           it.remove();
                        } else {
                           assert !invalidOffsets.contains(b.destination);
                        }
                     }

                     if (r instanceof OffsetBranch) {
                        OffsetBranch ob = (OffsetBranch)r;
                        if (invalidOffsets.contains(ob.source)) {
                           it.remove();
                        } else {
                           assert !invalidOffsets.contains(ob.destination);
                        }
                     }
                  }

                  it = this.exceptionTableEntries.iterator();

                  while(it.hasNext()) {
                     ExceptionTableEntry ete = (ExceptionTableEntry)it.next();
                     if (invalidOffsets.contains(ete.startPc)) {
                        assert invalidOffsets.contains(ete.endPc);

                        assert invalidOffsets.contains(ete.handlerPc);

                        it.remove();
                     } else {
                        assert !invalidOffsets.contains(ete.endPc);

                        assert !invalidOffsets.contains(ete.handlerPc);
                     }
                  }

                  it = this.allLocalVars.iterator();

                  while(it.hasNext()) {
                     Java.LocalVariableSlot var = (Java.LocalVariableSlot)it.next();
                     if (invalidOffsets.contains(var.getStart())) {
                        assert invalidOffsets.contains(var.getEnd());

                        it.remove();
                     } else {
                        assert !invalidOffsets.contains(var.getEnd());
                     }
                  }

                  from.next = to;
                  to.prev = from;
                  return;
               }

               o = o.next;
            } while($assertionsDisabled || o != null);

            throw new AssertionError();
         }
      }
   }

   public String toString() {
      return this.classFile.getThisClassName() + "/cio=" + this.currentInserter.offset;
   }

   public void pushOperand(String fieldDescriptor) {
      if (Descriptor.isReference(fieldDescriptor)) {
         this.pushObjectOperand(fieldDescriptor);
      } else if (!fieldDescriptor.equals("B") && !fieldDescriptor.equals("C") && !fieldDescriptor.equals("I") && !fieldDescriptor.equals("S") && !fieldDescriptor.equals("Z")) {
         if (fieldDescriptor.equals("D")) {
            this.pushDoubleOperand();
         } else if (fieldDescriptor.equals("F")) {
            this.pushFloatOperand();
         } else {
            if (!fieldDescriptor.equals("J")) {
               throw new AssertionError(fieldDescriptor);
            }

            this.pushLongOperand();
         }
      } else {
         this.pushIntOperand();
      }

   }

   public void pushTopOperand() {
      this.pushOperand(ClassFile.StackMapTableAttribute.TOP_VARIABLE_INFO);
   }

   public void pushIntOperand() {
      this.pushOperand(ClassFile.StackMapTableAttribute.INTEGER_VARIABLE_INFO);
   }

   public void pushLongOperand() {
      this.pushOperand(ClassFile.StackMapTableAttribute.LONG_VARIABLE_INFO);
   }

   public void pushFloatOperand() {
      this.pushOperand(ClassFile.StackMapTableAttribute.FLOAT_VARIABLE_INFO);
   }

   public void pushDoubleOperand() {
      this.pushOperand(ClassFile.StackMapTableAttribute.DOUBLE_VARIABLE_INFO);
   }

   public void pushNullOperand() {
      this.pushOperand(ClassFile.StackMapTableAttribute.NULL_VARIABLE_INFO);
   }

   public void pushUninitializedThisOperand() {
      this.pushOperand(ClassFile.StackMapTableAttribute.UNINITIALIZED_THIS_VARIABLE_INFO);
   }

   public void pushUninitializedOperand() {
      final Offset o = this.newOffset();
      final ClassFile.StackMapTableAttribute.UninitializedVariableInfo uvi = this.classFile.newUninitializedVariableInfo((short)o.offset);
      this.relocatables.add(new Relocatable() {
         public void grow() {
         }

         public void relocate() {
            uvi.offset = (short)o.offset;
         }
      });
      this.pushOperand((ClassFile.StackMapTableAttribute.VerificationTypeInfo)uvi);
   }

   public void pushObjectOperand(String fieldDescriptor) {
      this.pushOperand((ClassFile.StackMapTableAttribute.VerificationTypeInfo)this.classFile.newObjectVariableInfo(fieldDescriptor));
   }

   public void pushOperand(ClassFile.StackMapTableAttribute.VerificationTypeInfo topOperand) {
      Inserter ci = this.currentInserter();
      StackMap sm = ci.getStackMap();

      assert sm != null;

      sm = sm.pushOperand(topOperand);
      ci.setStackMap(sm);
      int ss = 0;

      for(ClassFile.StackMapTableAttribute.VerificationTypeInfo vti : sm.operands()) {
         ss += vti.category();
      }

      if (ss > this.maxStack) {
         this.maxStack = ss;
      }

   }

   public boolean peekNullOperand() {
      return this.peekOperand() == ClassFile.StackMapTableAttribute.NULL_VARIABLE_INFO;
   }

   public boolean peekObjectOperand() {
      return this.peekOperand() instanceof ClassFile.StackMapTableAttribute.ObjectVariableInfo;
   }

   public ClassFile.StackMapTableAttribute.VerificationTypeInfo peekOperand() {
      return this.currentInserter().getStackMap().peekOperand();
   }

   public ClassFile.StackMapTableAttribute.VerificationTypeInfo popOperand() {
      Inserter ci = this.currentInserter();
      StackMap sm = ci.getStackMap();

      ClassFile.StackMapTableAttribute.VerificationTypeInfo result;
      do {
         result = sm.peekOperand();
         sm = sm.popOperand();
      } while(result == ClassFile.StackMapTableAttribute.TOP_VARIABLE_INFO);

      ci.setStackMap(sm);
      return result;
   }

   public void popOperand(ClassFile.StackMapTableAttribute.VerificationTypeInfo expected) {
      ClassFile.StackMapTableAttribute.VerificationTypeInfo actual = this.popOperand();

      assert actual.equals(expected) : actual;

   }

   public void popOperand(String expectedFd) {
      ClassFile.StackMapTableAttribute.VerificationTypeInfo vti = this.popOperand();
      if (vti == ClassFile.StackMapTableAttribute.INTEGER_VARIABLE_INFO) {
         assert expectedFd.equals("Z") || expectedFd.equals("B") || expectedFd.equals("C") || expectedFd.equals("S") || expectedFd.equals("I") : expectedFd;
      } else if (vti == ClassFile.StackMapTableAttribute.LONG_VARIABLE_INFO) {
         assert expectedFd.equals("J") : expectedFd;
      } else if (vti == ClassFile.StackMapTableAttribute.FLOAT_VARIABLE_INFO) {
         assert expectedFd.equals("F") : expectedFd;
      } else if (vti == ClassFile.StackMapTableAttribute.DOUBLE_VARIABLE_INFO) {
         assert expectedFd.equals("D") : expectedFd;
      } else if (vti == ClassFile.StackMapTableAttribute.NULL_VARIABLE_INFO) {
         assert expectedFd.equals("V") : expectedFd;
      } else if (vti instanceof ClassFile.StackMapTableAttribute.ObjectVariableInfo) {
         assert Descriptor.isReference(expectedFd) : expectedFd + " vs. " + vti;

         ClassFile.StackMapTableAttribute.ObjectVariableInfo ovi = (ClassFile.StackMapTableAttribute.ObjectVariableInfo)vti;
         ClassFile.ConstantClassInfo cci = this.classFile.getConstantClassInfo(ovi.getConstantClassInfoIndex());
         String computationalTypeFd = Descriptor.fromInternalForm(cci.getName(this.classFile));

         assert expectedFd.equals(computationalTypeFd) : expectedFd + " vs. " + computationalTypeFd;
      } else {
         if (!(vti instanceof ClassFile.StackMapTableAttribute.UninitializedVariableInfo)) {
            throw new AssertionError(vti);
         }

         assert Descriptor.isReference(expectedFd) : expectedFd;
      }

   }

   public void popOperandAssignableTo(String declaredFd) {
      if (Descriptor.isPrimitive(declaredFd)) {
         this.popOperand(declaredFd);
      } else {
         this.popObjectOrUninitializedOrUninitializedThisOperand();
      }

   }

   public void popIntOperand() {
      this.popOperand(ClassFile.StackMapTableAttribute.INTEGER_VARIABLE_INFO);
   }

   public void popLongOperand() {
      this.popOperand(ClassFile.StackMapTableAttribute.LONG_VARIABLE_INFO);
   }

   public void popUninitializedThisOperand() {
      this.popOperand(ClassFile.StackMapTableAttribute.UNINITIALIZED_THIS_VARIABLE_INFO);
   }

   public void popUninitializedVariableOperand() {
      ClassFile.StackMapTableAttribute.VerificationTypeInfo op = this.popOperand();

      assert op instanceof ClassFile.StackMapTableAttribute.UninitializedVariableInfo : String.valueOf(op);

   }

   public void popReferenceOperand() {
      assert this.peekObjectOperand() || this.peekNullOperand() : this.peekOperand();

      this.popOperand();
   }

   public String popObjectOperand() {
      ClassFile.StackMapTableAttribute.VerificationTypeInfo vti = this.popOperand();

      assert vti instanceof ClassFile.StackMapTableAttribute.ObjectVariableInfo : vti;

      ClassFile.StackMapTableAttribute.ObjectVariableInfo ovi = (ClassFile.StackMapTableAttribute.ObjectVariableInfo)vti;
      short ccii = ovi.getConstantClassInfoIndex();
      ClassFile.ConstantClassInfo cci = this.classFile.getConstantClassInfo(ccii);
      return Descriptor.fromInternalForm(cci.getName(this.classFile));
   }

   public ClassFile.StackMapTableAttribute.VerificationTypeInfo popObjectOrUninitializedOrUninitializedThisOperand() {
      ClassFile.StackMapTableAttribute.VerificationTypeInfo result = this.popOperand();

      assert result instanceof ClassFile.StackMapTableAttribute.UninitializedVariableInfo || result instanceof ClassFile.StackMapTableAttribute.ObjectVariableInfo || result == ClassFile.StackMapTableAttribute.UNINITIALIZED_THIS_VARIABLE_INFO : result;

      return result;
   }

   public ClassFile.StackMapTableAttribute.VerificationTypeInfo popIntOrLongOperand() {
      ClassFile.StackMapTableAttribute.VerificationTypeInfo result = this.popOperand();

      assert result == ClassFile.StackMapTableAttribute.INTEGER_VARIABLE_INFO || result == ClassFile.StackMapTableAttribute.LONG_VARIABLE_INFO : result;

      return result;
   }

   static class LocalScope {
      @Nullable
      final LocalScope parent;
      final short startingLocalVariableSlot;
      final List localVars = new ArrayList();
      final StackMap startingStackMap;

      LocalScope(@Nullable LocalScope parent, short startingLocalSlot, StackMap startingStackMap) {
         this.parent = parent;
         this.startingLocalVariableSlot = startingLocalSlot;
         this.startingStackMap = startingStackMap;
      }
   }

   private class Branch extends Relocatable {
      private int opcode;
      private Inserter source;
      private final Offset destination;

      Branch(int opcode, Offset destination) {
         this.opcode = opcode;
         this.source = CodeContext.this.newInserter();
         this.destination = destination;
      }

      public void grow() {
         if (this.destination.offset == -1) {
            throw new InternalCompilerException("Cannot relocate branch to unset destination offset");
         } else {
            int offset = this.destination.offset - this.source.offset;
            int opcodeJsr = 168;
            if (this.opcode >= 167 && this.opcode <= 168) {
               if (offset > 32767 || offset < -32768) {
                  CodeContext.this.pushInserter(this.source);
                  this.source = CodeContext.this.newInserter();
                  CodeContext.this.makeSpace(-3);
                  CodeContext.this.writeBranch(this.opcode += 33, this.destination);
                  CodeContext.this.popInserter();
               }
            } else if ((this.opcode < 153 || this.opcode > 166) && (this.opcode < 198 || this.opcode > 199)) {
               if (this.opcode < 200 || this.opcode > 201) {
                  throw new AssertionError(this.opcode);
               }
            } else if (offset > 32767 || offset < -32768) {
               CodeContext.this.pushInserter(this.source);
               this.source = CodeContext.this.newInserter();
               CodeContext.this.makeSpace(-3);
               CodeContext var10002 = CodeContext.this;
               Objects.requireNonNull(var10002);
               BasicBlock skip = var10002.new BasicBlock();
               CodeContext.this.writeBranch(this.opcode = CodeContext.invertBranchOpcode(this.opcode), skip);
               if (this.opcode >= 153 && this.opcode <= 158) {
                  CodeContext.this.popIntOperand();
               } else if (this.opcode >= 159 && this.opcode <= 164) {
                  CodeContext.this.popIntOperand();
                  CodeContext.this.popIntOperand();
               } else if (this.opcode != 165 && this.opcode != 166) {
                  if (this.opcode != 198 && this.opcode != 199) {
                     throw new AssertionError(this.opcode);
                  }

                  CodeContext.this.popReferenceOperand();
               } else {
                  CodeContext.this.popReferenceOperand();
                  CodeContext.this.popReferenceOperand();
               }

               CodeContext.this.writeBranch(200, this.destination);
               skip.setStackMap(CodeContext.this.currentInserter.getStackMap());
               skip.set();
               CodeContext.this.popInserter();
            }

         }
      }

      public void relocate() {
         if (this.destination.offset == -1) {
            throw new InternalCompilerException("Cannot relocate branch to unset destination offset");
         } else {
            int opcodeJsr = 168;
            if ((this.opcode < 153 || this.opcode > 168) && (this.opcode < 198 || this.opcode > 199)) {
               if (this.opcode < 200 || this.opcode > 201) {
                  throw new AssertionError(this.opcode);
               }

               int offset = this.destination.offset - this.source.offset;
               CodeContext.this.code[this.source.offset + 1] = (byte)(offset >> 24);
               CodeContext.this.code[this.source.offset + 2] = (byte)(offset >> 16);
               CodeContext.this.code[this.source.offset + 3] = (byte)(offset >> 8);
               CodeContext.this.code[this.source.offset + 4] = (byte)offset;
            } else {
               int offset = this.destination.offset - this.source.offset;
               CodeContext.this.code[this.source.offset + 1] = (byte)(offset >> 8);
               CodeContext.this.code[this.source.offset + 2] = (byte)offset;
            }

         }
      }
   }

   private final class FourByteOffset extends Offset {
      private FourByteOffset() {
      }
   }

   private class OffsetBranch extends Relocatable {
      private final Offset where;
      private final Offset source;
      private final Offset destination;

      OffsetBranch(Offset where, Offset source, Offset destination) {
         this.where = where;
         this.source = source;
         this.destination = destination;
      }

      public void grow() {
      }

      public void relocate() {
         if (this.source.offset != -1 && this.destination.offset != -1) {
            int offset = this.destination.offset - this.source.offset;
            byte[] ba = new byte[]{(byte)(offset >> 24), (byte)(offset >> 16), (byte)(offset >> 8), (byte)offset};
            System.arraycopy(ba, 0, CodeContext.this.code, this.where.offset, 4);
         } else {
            throw new InternalCompilerException("Cannot relocate offset branch to unset destination offset");
         }
      }
   }

   public class Offset {
      int offset = -1;
      @Nullable
      Offset prev;
      @Nullable
      Offset next;
      static final int UNSET = -1;
      @Nullable
      private StackMap stackMap;

      public void set() {
         this.setOffset();
         this.setStackMap();
         Inserter ci = CodeContext.this.currentInserter;
         Offset cip = ci.prev;

         assert cip != null;

         this.prev = cip;
         this.next = ci;
         cip.next = this;
         ci.prev = this;
      }

      public void setBasicBlock() {
         this.set();

         assert CodeContext.this.currentInserter.getStackMap() != null;

         (CodeContext.this.new BasicBlock()).set();
      }

      void setStackMap() {
         Inserter ci = CodeContext.this.currentInserter;
         ci.stackMap = this.stackMap = CodeContext.mergeStackMaps(ci.stackMap, this.stackMap);
      }

      public void setOffset() {
         Inserter ci = CodeContext.this.currentInserter;
         if (this.offset != -1) {
            throw new InternalCompilerException("Offset already set");
         } else {
            this.offset = ci.offset;
         }
      }

      public StackMap getStackMap() {
         return this.stackMap;
      }

      public void setStackMap(StackMap stackMap) {
         this.stackMap = stackMap;
      }

      public final CodeContext getCodeContext() {
         return CodeContext.this;
      }

      public String toString() {
         return CodeContext.this.classFile.getThisClassName() + ": " + this.offset;
      }
   }

   private static class ExceptionTableEntry {
      final Offset startPc;
      final Offset endPc;
      final Offset handlerPc;
      final short catchType;

      ExceptionTableEntry(Offset startPc, Offset endPc, Offset handlerPc, short catchType) {
         this.startPc = startPc;
         this.endPc = endPc;
         this.handlerPc = handlerPc;
         this.catchType = catchType;
      }
   }

   public class Inserter extends Offset {
      @Nullable
      private Inserter nextInserter;
   }

   public class LineNumberOffset extends Offset {
      private final short lineNumber;

      public LineNumberOffset(int offset, StackMap stackMap, short lineNumber) {
         this.lineNumber = lineNumber;
         this.offset = offset;
         this.setStackMap(stackMap);
      }
   }

   public final class BasicBlock extends Offset {
   }

   private abstract class Relocatable {
      private Relocatable() {
      }

      public abstract void grow();

      public abstract void relocate();
   }

   public interface FixUp {
      void fixUp();
   }
}
