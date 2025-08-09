package org.stringtemplate.v4.compiler;

import java.util.ArrayList;
import java.util.List;
import org.stringtemplate.v4.misc.Interval;
import org.stringtemplate.v4.misc.Misc;

public class BytecodeDisassembler {
   CompiledST code;

   public BytecodeDisassembler(CompiledST code) {
      this.code = code;
   }

   public String instrs() {
      StringBuilder buf = new StringBuilder();
      int ip = 0;

      while(ip < this.code.codeSize) {
         if (ip > 0) {
            buf.append(", ");
         }

         int opcode = this.code.instrs[ip];
         Bytecode.Instruction I = Bytecode.instructions[opcode];
         buf.append(I.name);
         ++ip;

         for(int opnd = 0; opnd < I.nopnds; ++opnd) {
            buf.append(' ');
            buf.append(getShort(this.code.instrs, ip));
            ip += 2;
         }
      }

      return buf.toString();
   }

   public String disassemble() {
      StringBuilder buf = new StringBuilder();
      int i = 0;

      while(i < this.code.codeSize) {
         i = this.disassembleInstruction(buf, i);
         buf.append('\n');
      }

      return buf.toString();
   }

   public int disassembleInstruction(StringBuilder buf, int ip) {
      int opcode = this.code.instrs[ip];
      if (ip >= this.code.codeSize) {
         throw new IllegalArgumentException("ip out of range: " + ip);
      } else {
         Bytecode.Instruction I = Bytecode.instructions[opcode];
         if (I == null) {
            throw new IllegalArgumentException("no such instruction " + opcode + " at address " + ip);
         } else {
            String instrName = I.name;
            buf.append(String.format("%04d:\t%-14s", ip, instrName));
            ++ip;
            if (I.nopnds == 0) {
               buf.append("  ");
               return ip;
            } else {
               List<String> operands = new ArrayList();

               for(int i = 0; i < I.nopnds; ++i) {
                  int opnd = getShort(this.code.instrs, ip);
                  ip += 2;
                  switch (I.type[i]) {
                     case STRING:
                        operands.add(this.showConstPoolOperand(opnd));
                        break;
                     case ADDR:
                     case INT:
                        operands.add(String.valueOf(opnd));
                        break;
                     default:
                        operands.add(String.valueOf(opnd));
                  }
               }

               for(int i = 0; i < operands.size(); ++i) {
                  String s = (String)operands.get(i);
                  if (i > 0) {
                     buf.append(", ");
                  }

                  buf.append(s);
               }

               return ip;
            }
         }
      }
   }

   private String showConstPoolOperand(int poolIndex) {
      StringBuffer buf = new StringBuffer();
      buf.append("#");
      buf.append(poolIndex);
      String s = "<bad string index>";
      if (poolIndex < this.code.strings.length) {
         if (this.code.strings[poolIndex] == null) {
            s = "null";
         } else {
            s = this.code.strings[poolIndex].toString();
            if (this.code.strings[poolIndex] instanceof String) {
               s = Misc.replaceEscapes(s);
               s = '"' + s + '"';
            }
         }
      }

      buf.append(":");
      buf.append(s);
      return buf.toString();
   }

   public static int getShort(byte[] memory, int index) {
      int b1 = memory[index] & 255;
      int b2 = memory[index + 1] & 255;
      int word = b1 << 8 | b2;
      return word;
   }

   public String strings() {
      StringBuffer buf = new StringBuffer();
      int addr = 0;
      if (this.code.strings != null) {
         for(Object o : this.code.strings) {
            if (o instanceof String) {
               String s = (String)o;
               s = Misc.replaceEscapes(s);
               buf.append(String.format("%04d: \"%s\"\n", addr, s));
            } else {
               buf.append(String.format("%04d: %s\n", addr, o));
            }

            ++addr;
         }
      }

      return buf.toString();
   }

   public String sourceMap() {
      StringBuffer buf = new StringBuffer();
      int addr = 0;

      for(Interval I : this.code.sourceMap) {
         if (I != null) {
            String chunk = this.code.template.substring(I.a, I.b + 1);
            buf.append(String.format("%04d: %s\t\"%s\"\n", addr, I, chunk));
         }

         ++addr;
      }

      return buf.toString();
   }
}
