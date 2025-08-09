package io.vertx.core.http;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject
public class StreamPriority {
   public static final int DEFAULT_DEPENDENCY = 0;
   public static final short DEFAULT_WEIGHT = 16;
   public static final boolean DEFAULT_EXCLUSIVE = false;
   private short weight;
   private int dependency;
   private boolean exclusive;

   public StreamPriority() {
      this.weight = 16;
      this.dependency = 0;
      this.exclusive = false;
   }

   public StreamPriority(JsonObject json) {
      this.weight = json.getInteger("weight", 16).shortValue();
      this.dependency = json.getInteger("dependency", 0);
      this.exclusive = json.getBoolean("exclusive", false);
   }

   public StreamPriority(StreamPriority other) {
      this.weight = other.weight;
      this.dependency = other.dependency;
      this.exclusive = other.exclusive;
   }

   public short getWeight() {
      return this.weight;
   }

   public StreamPriority setWeight(short weight) {
      this.weight = weight;
      return this;
   }

   public int getDependency() {
      return this.dependency;
   }

   public StreamPriority setDependency(int dependency) {
      this.dependency = dependency;
      return this;
   }

   public boolean isExclusive() {
      return this.exclusive;
   }

   public StreamPriority setExclusive(boolean exclusive) {
      this.exclusive = exclusive;
      return this;
   }

   public int hashCode() {
      int prime = 31;
      int result = 1;
      result = 31 * result + (this.exclusive ? 1231 : 1237);
      result = 31 * result + this.dependency;
      result = 31 * result + this.weight;
      return result;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         StreamPriority other = (StreamPriority)obj;
         if (this.exclusive != other.exclusive) {
            return false;
         } else if (this.dependency != other.dependency) {
            return false;
         } else {
            return this.weight == other.weight;
         }
      }
   }

   public JsonObject toJson() {
      JsonObject json = new JsonObject();
      json.put("weight", this.weight);
      json.put("dependency", this.dependency);
      json.put("exclusive", this.exclusive);
      return json;
   }

   public String toString() {
      return "StreamPriority [weight=" + this.weight + ", dependency=" + this.dependency + ", exclusive=" + this.exclusive + "]";
   }
}
