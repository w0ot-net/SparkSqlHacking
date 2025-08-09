package org.jline.terminal;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Attributes {
   final EnumSet iflag = EnumSet.noneOf(InputFlag.class);
   final EnumSet oflag = EnumSet.noneOf(OutputFlag.class);
   final EnumSet cflag = EnumSet.noneOf(ControlFlag.class);
   final EnumSet lflag = EnumSet.noneOf(LocalFlag.class);
   final EnumMap cchars = new EnumMap(ControlChar.class);

   public Attributes() {
   }

   public Attributes(Attributes attr) {
      this.copy(attr);
   }

   public EnumSet getInputFlags() {
      return this.iflag;
   }

   public void setInputFlags(EnumSet flags) {
      this.iflag.clear();
      this.iflag.addAll(flags);
   }

   public boolean getInputFlag(InputFlag flag) {
      return this.iflag.contains(flag);
   }

   public void setInputFlags(EnumSet flags, boolean value) {
      if (value) {
         this.iflag.addAll(flags);
      } else {
         this.iflag.removeAll(flags);
      }

   }

   public void setInputFlag(InputFlag flag, boolean value) {
      if (value) {
         this.iflag.add(flag);
      } else {
         this.iflag.remove(flag);
      }

   }

   public EnumSet getOutputFlags() {
      return this.oflag;
   }

   public void setOutputFlags(EnumSet flags) {
      this.oflag.clear();
      this.oflag.addAll(flags);
   }

   public boolean getOutputFlag(OutputFlag flag) {
      return this.oflag.contains(flag);
   }

   public void setOutputFlags(EnumSet flags, boolean value) {
      if (value) {
         this.oflag.addAll(flags);
      } else {
         this.oflag.removeAll(flags);
      }

   }

   public void setOutputFlag(OutputFlag flag, boolean value) {
      if (value) {
         this.oflag.add(flag);
      } else {
         this.oflag.remove(flag);
      }

   }

   public EnumSet getControlFlags() {
      return this.cflag;
   }

   public void setControlFlags(EnumSet flags) {
      this.cflag.clear();
      this.cflag.addAll(flags);
   }

   public boolean getControlFlag(ControlFlag flag) {
      return this.cflag.contains(flag);
   }

   public void setControlFlags(EnumSet flags, boolean value) {
      if (value) {
         this.cflag.addAll(flags);
      } else {
         this.cflag.removeAll(flags);
      }

   }

   public void setControlFlag(ControlFlag flag, boolean value) {
      if (value) {
         this.cflag.add(flag);
      } else {
         this.cflag.remove(flag);
      }

   }

   public EnumSet getLocalFlags() {
      return this.lflag;
   }

   public void setLocalFlags(EnumSet flags) {
      this.lflag.clear();
      this.lflag.addAll(flags);
   }

   public boolean getLocalFlag(LocalFlag flag) {
      return this.lflag.contains(flag);
   }

   public void setLocalFlags(EnumSet flags, boolean value) {
      if (value) {
         this.lflag.addAll(flags);
      } else {
         this.lflag.removeAll(flags);
      }

   }

   public void setLocalFlag(LocalFlag flag, boolean value) {
      if (value) {
         this.lflag.add(flag);
      } else {
         this.lflag.remove(flag);
      }

   }

   public EnumMap getControlChars() {
      return this.cchars;
   }

   public void setControlChars(EnumMap chars) {
      this.cchars.clear();
      this.cchars.putAll(chars);
   }

   public int getControlChar(ControlChar c) {
      Integer v = (Integer)this.cchars.get(c);
      return v != null ? v : -1;
   }

   public void setControlChar(ControlChar c, int value) {
      this.cchars.put(c, value);
   }

   public void copy(Attributes attributes) {
      this.setControlFlags(attributes.getControlFlags());
      this.setInputFlags(attributes.getInputFlags());
      this.setLocalFlags(attributes.getLocalFlags());
      this.setOutputFlags(attributes.getOutputFlags());
      this.setControlChars(attributes.getControlChars());
   }

   public String toString() {
      return "Attributes[lflags: " + this.append(this.lflag) + ", iflags: " + this.append(this.iflag) + ", oflags: " + this.append(this.oflag) + ", cflags: " + this.append(this.cflag) + ", cchars: " + this.append(EnumSet.allOf(ControlChar.class), this::display) + "]";
   }

   private String display(ControlChar c) {
      int ch = this.getControlChar(c);
      String value;
      if (c != Attributes.ControlChar.VMIN && c != Attributes.ControlChar.VTIME) {
         if (ch < 0) {
            value = "<undef>";
         } else if (ch < 32) {
            value = "^" + (char)(ch + 65 - 1);
         } else if (ch == 127) {
            value = "^?";
         } else if (ch >= 128) {
            value = String.format("\\u%04x", ch);
         } else {
            value = String.valueOf((char)ch);
         }
      } else {
         value = Integer.toString(ch);
      }

      return c.name().toLowerCase().substring(1) + "=" + value;
   }

   private String append(EnumSet set) {
      return this.append(set, (e) -> e.name().toLowerCase());
   }

   private String append(EnumSet set, Function toString) {
      return (String)set.stream().map(toString).collect(Collectors.joining(" "));
   }

   public static enum ControlChar {
      VEOF,
      VEOL,
      VEOL2,
      VERASE,
      VWERASE,
      VKILL,
      VREPRINT,
      VINTR,
      VQUIT,
      VSUSP,
      VDSUSP,
      VSTART,
      VSTOP,
      VLNEXT,
      VDISCARD,
      VMIN,
      VTIME,
      VSTATUS;

      // $FF: synthetic method
      private static ControlChar[] $values() {
         return new ControlChar[]{VEOF, VEOL, VEOL2, VERASE, VWERASE, VKILL, VREPRINT, VINTR, VQUIT, VSUSP, VDSUSP, VSTART, VSTOP, VLNEXT, VDISCARD, VMIN, VTIME, VSTATUS};
      }
   }

   public static enum InputFlag {
      IGNBRK,
      BRKINT,
      IGNPAR,
      PARMRK,
      INPCK,
      ISTRIP,
      INLCR,
      IGNCR,
      ICRNL,
      IXON,
      IXOFF,
      IXANY,
      IMAXBEL,
      IUTF8,
      INORMEOL;

      // $FF: synthetic method
      private static InputFlag[] $values() {
         return new InputFlag[]{IGNBRK, BRKINT, IGNPAR, PARMRK, INPCK, ISTRIP, INLCR, IGNCR, ICRNL, IXON, IXOFF, IXANY, IMAXBEL, IUTF8, INORMEOL};
      }
   }

   public static enum OutputFlag {
      OPOST,
      ONLCR,
      OXTABS,
      ONOEOT,
      OCRNL,
      ONOCR,
      ONLRET,
      OFILL,
      NLDLY,
      TABDLY,
      CRDLY,
      FFDLY,
      BSDLY,
      VTDLY,
      OFDEL;

      // $FF: synthetic method
      private static OutputFlag[] $values() {
         return new OutputFlag[]{OPOST, ONLCR, OXTABS, ONOEOT, OCRNL, ONOCR, ONLRET, OFILL, NLDLY, TABDLY, CRDLY, FFDLY, BSDLY, VTDLY, OFDEL};
      }
   }

   public static enum ControlFlag {
      CIGNORE,
      CS5,
      CS6,
      CS7,
      CS8,
      CSTOPB,
      CREAD,
      PARENB,
      PARODD,
      HUPCL,
      CLOCAL,
      CCTS_OFLOW,
      CRTS_IFLOW,
      CDTR_IFLOW,
      CDSR_OFLOW,
      CCAR_OFLOW;

      // $FF: synthetic method
      private static ControlFlag[] $values() {
         return new ControlFlag[]{CIGNORE, CS5, CS6, CS7, CS8, CSTOPB, CREAD, PARENB, PARODD, HUPCL, CLOCAL, CCTS_OFLOW, CRTS_IFLOW, CDTR_IFLOW, CDSR_OFLOW, CCAR_OFLOW};
      }
   }

   public static enum LocalFlag {
      ECHOKE,
      ECHOE,
      ECHOK,
      ECHO,
      ECHONL,
      ECHOPRT,
      ECHOCTL,
      ISIG,
      ICANON,
      ALTWERASE,
      IEXTEN,
      EXTPROC,
      TOSTOP,
      FLUSHO,
      NOKERNINFO,
      PENDIN,
      NOFLSH;

      // $FF: synthetic method
      private static LocalFlag[] $values() {
         return new LocalFlag[]{ECHOKE, ECHOE, ECHOK, ECHO, ECHONL, ECHOPRT, ECHOCTL, ISIG, ICANON, ALTWERASE, IEXTEN, EXTPROC, TOSTOP, FLUSHO, NOKERNINFO, PENDIN, NOFLSH};
      }
   }
}
