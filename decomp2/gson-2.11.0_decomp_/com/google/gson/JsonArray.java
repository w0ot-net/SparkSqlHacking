package com.google.gson;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.gson.internal.NonNullElementWrapperList;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class JsonArray extends JsonElement implements Iterable {
   private final ArrayList elements;

   public JsonArray() {
      this.elements = new ArrayList();
   }

   public JsonArray(int capacity) {
      this.elements = new ArrayList(capacity);
   }

   public JsonArray deepCopy() {
      if (this.elements.isEmpty()) {
         return new JsonArray();
      } else {
         JsonArray result = new JsonArray(this.elements.size());

         for(JsonElement element : this.elements) {
            result.add(element.deepCopy());
         }

         return result;
      }
   }

   public void add(Boolean bool) {
      this.elements.add(bool == null ? JsonNull.INSTANCE : new JsonPrimitive(bool));
   }

   public void add(Character character) {
      this.elements.add(character == null ? JsonNull.INSTANCE : new JsonPrimitive(character));
   }

   public void add(Number number) {
      this.elements.add(number == null ? JsonNull.INSTANCE : new JsonPrimitive(number));
   }

   public void add(String string) {
      this.elements.add(string == null ? JsonNull.INSTANCE : new JsonPrimitive(string));
   }

   public void add(JsonElement element) {
      if (element == null) {
         element = JsonNull.INSTANCE;
      }

      this.elements.add(element);
   }

   public void addAll(JsonArray array) {
      this.elements.addAll(array.elements);
   }

   @CanIgnoreReturnValue
   public JsonElement set(int index, JsonElement element) {
      return (JsonElement)this.elements.set(index, element == null ? JsonNull.INSTANCE : element);
   }

   @CanIgnoreReturnValue
   public boolean remove(JsonElement element) {
      return this.elements.remove(element);
   }

   @CanIgnoreReturnValue
   public JsonElement remove(int index) {
      return (JsonElement)this.elements.remove(index);
   }

   public boolean contains(JsonElement element) {
      return this.elements.contains(element);
   }

   public int size() {
      return this.elements.size();
   }

   public boolean isEmpty() {
      return this.elements.isEmpty();
   }

   public Iterator iterator() {
      return this.elements.iterator();
   }

   public JsonElement get(int i) {
      return (JsonElement)this.elements.get(i);
   }

   private JsonElement getAsSingleElement() {
      int size = this.elements.size();
      if (size == 1) {
         return (JsonElement)this.elements.get(0);
      } else {
         throw new IllegalStateException("Array must have size 1, but has size " + size);
      }
   }

   public Number getAsNumber() {
      return this.getAsSingleElement().getAsNumber();
   }

   public String getAsString() {
      return this.getAsSingleElement().getAsString();
   }

   public double getAsDouble() {
      return this.getAsSingleElement().getAsDouble();
   }

   public BigDecimal getAsBigDecimal() {
      return this.getAsSingleElement().getAsBigDecimal();
   }

   public BigInteger getAsBigInteger() {
      return this.getAsSingleElement().getAsBigInteger();
   }

   public float getAsFloat() {
      return this.getAsSingleElement().getAsFloat();
   }

   public long getAsLong() {
      return this.getAsSingleElement().getAsLong();
   }

   public int getAsInt() {
      return this.getAsSingleElement().getAsInt();
   }

   public byte getAsByte() {
      return this.getAsSingleElement().getAsByte();
   }

   /** @deprecated */
   @Deprecated
   public char getAsCharacter() {
      return this.getAsSingleElement().getAsCharacter();
   }

   public short getAsShort() {
      return this.getAsSingleElement().getAsShort();
   }

   public boolean getAsBoolean() {
      return this.getAsSingleElement().getAsBoolean();
   }

   public List asList() {
      return new NonNullElementWrapperList(this.elements);
   }

   public boolean equals(Object o) {
      return o == this || o instanceof JsonArray && ((JsonArray)o).elements.equals(this.elements);
   }

   public int hashCode() {
      return this.elements.hashCode();
   }
}
