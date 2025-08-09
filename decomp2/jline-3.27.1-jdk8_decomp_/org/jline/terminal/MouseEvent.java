package org.jline.terminal;

import java.util.EnumSet;

public class MouseEvent {
   private final Type type;
   private final Button button;
   private final EnumSet modifiers;
   private final int x;
   private final int y;

   public MouseEvent(Type type, Button button, EnumSet modifiers, int x, int y) {
      this.type = type;
      this.button = button;
      this.modifiers = modifiers;
      this.x = x;
      this.y = y;
   }

   public Type getType() {
      return this.type;
   }

   public Button getButton() {
      return this.button;
   }

   public EnumSet getModifiers() {
      return this.modifiers;
   }

   public int getX() {
      return this.x;
   }

   public int getY() {
      return this.y;
   }

   public String toString() {
      return "MouseEvent[type=" + this.type + ", button=" + this.button + ", modifiers=" + this.modifiers + ", x=" + this.x + ", y=" + this.y + ']';
   }

   public static enum Type {
      Released,
      Pressed,
      Wheel,
      Moved,
      Dragged;

      // $FF: synthetic method
      private static Type[] $values() {
         return new Type[]{Released, Pressed, Wheel, Moved, Dragged};
      }
   }

   public static enum Button {
      NoButton,
      Button1,
      Button2,
      Button3,
      WheelUp,
      WheelDown;

      // $FF: synthetic method
      private static Button[] $values() {
         return new Button[]{NoButton, Button1, Button2, Button3, WheelUp, WheelDown};
      }
   }

   public static enum Modifier {
      Shift,
      Alt,
      Control;

      // $FF: synthetic method
      private static Modifier[] $values() {
         return new Modifier[]{Shift, Alt, Control};
      }
   }
}
