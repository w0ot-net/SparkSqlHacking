package org.apache.derby.iapi.services.io;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import org.apache.derby.iapi.services.context.Context;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.services.loader.ClassFactoryContext;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.types.Resetable;
import org.apache.derby.shared.common.error.StandardException;

public final class FormatIdInputStream extends DataInputStream implements ErrorObjectInput, Resetable, CloneableStream {
   protected ClassFactory cf;
   private ErrorInfo errorInfo;
   private Exception myNestedException;

   public FormatIdInputStream(InputStream var1) {
      super(var1);
   }

   public Object readObject() throws IOException, ClassNotFoundException {
      this.setErrorInfo((ErrorInfo)null);
      int var1 = FormatIdUtil.readFormatIdInteger((DataInput)this);
      if (var1 == 0) {
         return null;
      } else if (var1 == 1) {
         return this.readUTF();
      } else {
         try {
            if (var1 == 2) {
               ObjectInputStream var11 = this.getObjectStream();

               try {
                  Object var13 = var11.readObject();
                  return var13;
               } catch (IOException var5) {
                  throw (IOException)this.handleReadError(var5, var11);
               } catch (ClassNotFoundException var6) {
                  throw (ClassNotFoundException)this.handleReadError(var6, var11);
               } catch (LinkageError var7) {
                  throw (LinkageError)this.handleReadError(var7, var11);
               } catch (ClassCastException var8) {
                  throw (ClassCastException)this.handleReadError(var8, var11);
               }
            } else {
               try {
                  Formatable var2 = (Formatable)Monitor.newInstanceFromIdentifier(var1);
                  if (var2 instanceof Storable) {
                     boolean var12 = this.readBoolean();
                     if (var12) {
                        Storable var4 = (Storable)var2;
                        var4.restoreToNull();
                        return var4;
                     }
                  }

                  var2.readExternal(this);
                  return var2;
               } catch (StandardException var9) {
                  throw new ClassNotFoundException(var9.toString());
               }
            }
         } catch (ClassCastException var10) {
            StreamCorruptedException var3 = new StreamCorruptedException(var10.toString());
            var3.initCause(var10);
            throw var3;
         }
      }
   }

   public void setInput(InputStream var1) {
      this.in = var1;
   }

   public InputStream getInputStream() {
      return this.in;
   }

   public String getErrorInfo() {
      return this.errorInfo == null ? "" : this.errorInfo.getErrorInfo();
   }

   public Exception getNestedException() {
      if (this.myNestedException != null) {
         return null;
      } else {
         return this.errorInfo == null ? null : this.errorInfo.getNestedException();
      }
   }

   private void setErrorInfo(ErrorInfo var1) {
      this.errorInfo = var1;
   }

   private Throwable handleReadError(Throwable var1, ObjectInputStream var2) {
      if (var2 instanceof ErrorInfo) {
         this.setErrorInfo((ErrorInfo)var2);
      }

      return var1;
   }

   ClassFactory getClassFactory() {
      if (this.cf == null) {
         ClassFactoryContext var1 = (ClassFactoryContext)getContextOrNull("ClassFactoryContext");
         if (var1 != null) {
            this.cf = var1.getClassFactory();
         }
      }

      return this.cf;
   }

   private ObjectInputStream getObjectStream() throws IOException {
      return (ObjectInputStream)(this.getClassFactory() == null ? new ObjectInputStream(this) : new ApplicationObjectInputStream(this, this.cf));
   }

   public void resetStream() throws IOException, StandardException {
      ((Resetable)this.in).resetStream();
   }

   public void initStream() throws StandardException {
      ((Resetable)this.in).initStream();
   }

   public void closeStream() {
      ((Resetable)this.in).closeStream();
   }

   public InputStream cloneStream() {
      InputStream var1 = ((CloneableStream)this.in).cloneStream();
      return new FormatIdInputStream(var1);
   }

   private static Context getContextOrNull(String var0) {
      return ContextService.getContextOrNull(var0);
   }
}
