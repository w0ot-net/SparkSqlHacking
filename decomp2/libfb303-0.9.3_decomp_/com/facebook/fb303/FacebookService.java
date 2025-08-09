package com.facebook.fb303;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.thrift.AsyncProcessFunction;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.ProcessFunction;
import org.apache.thrift.TApplicationException;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseAsyncProcessor;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TBaseProcessor;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.TProcessor;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.TServiceClientFactory;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClient;
import org.apache.thrift.async.TAsyncClientFactory;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.async.TAsyncMethodCall;
import org.apache.thrift.async.TAsyncMethodCall.State;
import org.apache.thrift.meta_data.EnumMetaData;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.MapMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TMap;
import org.apache.thrift.protocol.TMessage;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.server.AbstractNonblockingServer;
import org.apache.thrift.transport.TIOStreamTransport;
import org.apache.thrift.transport.TMemoryInputTransport;
import org.apache.thrift.transport.TNonblockingTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FacebookService {
   public static class Client extends TServiceClient implements Iface {
      public Client(TProtocol prot) {
         super(prot, prot);
      }

      public Client(TProtocol iprot, TProtocol oprot) {
         super(iprot, oprot);
      }

      public String getName() throws TException {
         this.send_getName();
         return this.recv_getName();
      }

      public void send_getName() throws TException {
         getName_args args = new getName_args();
         this.sendBase("getName", args);
      }

      public String recv_getName() throws TException {
         getName_result result = new getName_result();
         this.receiveBase(result, "getName");
         if (result.isSetSuccess()) {
            return result.success;
         } else {
            throw new TApplicationException(5, "getName failed: unknown result");
         }
      }

      public String getVersion() throws TException {
         this.send_getVersion();
         return this.recv_getVersion();
      }

      public void send_getVersion() throws TException {
         getVersion_args args = new getVersion_args();
         this.sendBase("getVersion", args);
      }

      public String recv_getVersion() throws TException {
         getVersion_result result = new getVersion_result();
         this.receiveBase(result, "getVersion");
         if (result.isSetSuccess()) {
            return result.success;
         } else {
            throw new TApplicationException(5, "getVersion failed: unknown result");
         }
      }

      public fb_status getStatus() throws TException {
         this.send_getStatus();
         return this.recv_getStatus();
      }

      public void send_getStatus() throws TException {
         getStatus_args args = new getStatus_args();
         this.sendBase("getStatus", args);
      }

      public fb_status recv_getStatus() throws TException {
         getStatus_result result = new getStatus_result();
         this.receiveBase(result, "getStatus");
         if (result.isSetSuccess()) {
            return result.success;
         } else {
            throw new TApplicationException(5, "getStatus failed: unknown result");
         }
      }

      public String getStatusDetails() throws TException {
         this.send_getStatusDetails();
         return this.recv_getStatusDetails();
      }

      public void send_getStatusDetails() throws TException {
         getStatusDetails_args args = new getStatusDetails_args();
         this.sendBase("getStatusDetails", args);
      }

      public String recv_getStatusDetails() throws TException {
         getStatusDetails_result result = new getStatusDetails_result();
         this.receiveBase(result, "getStatusDetails");
         if (result.isSetSuccess()) {
            return result.success;
         } else {
            throw new TApplicationException(5, "getStatusDetails failed: unknown result");
         }
      }

      public Map getCounters() throws TException {
         this.send_getCounters();
         return this.recv_getCounters();
      }

      public void send_getCounters() throws TException {
         getCounters_args args = new getCounters_args();
         this.sendBase("getCounters", args);
      }

      public Map recv_getCounters() throws TException {
         getCounters_result result = new getCounters_result();
         this.receiveBase(result, "getCounters");
         if (result.isSetSuccess()) {
            return result.success;
         } else {
            throw new TApplicationException(5, "getCounters failed: unknown result");
         }
      }

      public long getCounter(String key) throws TException {
         this.send_getCounter(key);
         return this.recv_getCounter();
      }

      public void send_getCounter(String key) throws TException {
         getCounter_args args = new getCounter_args();
         args.setKey(key);
         this.sendBase("getCounter", args);
      }

      public long recv_getCounter() throws TException {
         getCounter_result result = new getCounter_result();
         this.receiveBase(result, "getCounter");
         if (result.isSetSuccess()) {
            return result.success;
         } else {
            throw new TApplicationException(5, "getCounter failed: unknown result");
         }
      }

      public void setOption(String key, String value) throws TException {
         this.send_setOption(key, value);
         this.recv_setOption();
      }

      public void send_setOption(String key, String value) throws TException {
         setOption_args args = new setOption_args();
         args.setKey(key);
         args.setValue(value);
         this.sendBase("setOption", args);
      }

      public void recv_setOption() throws TException {
         setOption_result result = new setOption_result();
         this.receiveBase(result, "setOption");
      }

      public String getOption(String key) throws TException {
         this.send_getOption(key);
         return this.recv_getOption();
      }

      public void send_getOption(String key) throws TException {
         getOption_args args = new getOption_args();
         args.setKey(key);
         this.sendBase("getOption", args);
      }

      public String recv_getOption() throws TException {
         getOption_result result = new getOption_result();
         this.receiveBase(result, "getOption");
         if (result.isSetSuccess()) {
            return result.success;
         } else {
            throw new TApplicationException(5, "getOption failed: unknown result");
         }
      }

      public Map getOptions() throws TException {
         this.send_getOptions();
         return this.recv_getOptions();
      }

      public void send_getOptions() throws TException {
         getOptions_args args = new getOptions_args();
         this.sendBase("getOptions", args);
      }

      public Map recv_getOptions() throws TException {
         getOptions_result result = new getOptions_result();
         this.receiveBase(result, "getOptions");
         if (result.isSetSuccess()) {
            return result.success;
         } else {
            throw new TApplicationException(5, "getOptions failed: unknown result");
         }
      }

      public String getCpuProfile(int profileDurationInSec) throws TException {
         this.send_getCpuProfile(profileDurationInSec);
         return this.recv_getCpuProfile();
      }

      public void send_getCpuProfile(int profileDurationInSec) throws TException {
         getCpuProfile_args args = new getCpuProfile_args();
         args.setProfileDurationInSec(profileDurationInSec);
         this.sendBase("getCpuProfile", args);
      }

      public String recv_getCpuProfile() throws TException {
         getCpuProfile_result result = new getCpuProfile_result();
         this.receiveBase(result, "getCpuProfile");
         if (result.isSetSuccess()) {
            return result.success;
         } else {
            throw new TApplicationException(5, "getCpuProfile failed: unknown result");
         }
      }

      public long aliveSince() throws TException {
         this.send_aliveSince();
         return this.recv_aliveSince();
      }

      public void send_aliveSince() throws TException {
         aliveSince_args args = new aliveSince_args();
         this.sendBase("aliveSince", args);
      }

      public long recv_aliveSince() throws TException {
         aliveSince_result result = new aliveSince_result();
         this.receiveBase(result, "aliveSince");
         if (result.isSetSuccess()) {
            return result.success;
         } else {
            throw new TApplicationException(5, "aliveSince failed: unknown result");
         }
      }

      public void reinitialize() throws TException {
         this.send_reinitialize();
      }

      public void send_reinitialize() throws TException {
         reinitialize_args args = new reinitialize_args();
         this.sendBaseOneway("reinitialize", args);
      }

      public void shutdown() throws TException {
         this.send_shutdown();
      }

      public void send_shutdown() throws TException {
         shutdown_args args = new shutdown_args();
         this.sendBaseOneway("shutdown", args);
      }

      public static class Factory implements TServiceClientFactory {
         public Client getClient(TProtocol prot) {
            return new Client(prot);
         }

         public Client getClient(TProtocol iprot, TProtocol oprot) {
            return new Client(iprot, oprot);
         }
      }
   }

   public static class AsyncClient extends TAsyncClient implements AsyncIface {
      public AsyncClient(TProtocolFactory protocolFactory, TAsyncClientManager clientManager, TNonblockingTransport transport) {
         super(protocolFactory, clientManager, transport);
      }

      public void getName(AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         getName_call method_call = new getName_call(resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public void getVersion(AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         getVersion_call method_call = new getVersion_call(resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public void getStatus(AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         getStatus_call method_call = new getStatus_call(resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public void getStatusDetails(AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         getStatusDetails_call method_call = new getStatusDetails_call(resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public void getCounters(AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         getCounters_call method_call = new getCounters_call(resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public void getCounter(String key, AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         getCounter_call method_call = new getCounter_call(key, resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public void setOption(String key, String value, AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         setOption_call method_call = new setOption_call(key, value, resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public void getOption(String key, AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         getOption_call method_call = new getOption_call(key, resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public void getOptions(AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         getOptions_call method_call = new getOptions_call(resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public void getCpuProfile(int profileDurationInSec, AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         getCpuProfile_call method_call = new getCpuProfile_call(profileDurationInSec, resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public void aliveSince(AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         aliveSince_call method_call = new aliveSince_call(resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public void reinitialize(AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         reinitialize_call method_call = new reinitialize_call(resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public void shutdown(AsyncMethodCallback resultHandler) throws TException {
         this.checkReady();
         shutdown_call method_call = new shutdown_call(resultHandler, this, this.___protocolFactory, this.___transport);
         this.___currentMethod = method_call;
         this.___manager.call(method_call);
      }

      public static class Factory implements TAsyncClientFactory {
         private TAsyncClientManager clientManager;
         private TProtocolFactory protocolFactory;

         public Factory(TAsyncClientManager clientManager, TProtocolFactory protocolFactory) {
            this.clientManager = clientManager;
            this.protocolFactory = protocolFactory;
         }

         public AsyncClient getAsyncClient(TNonblockingTransport transport) {
            return new AsyncClient(this.protocolFactory, this.clientManager, transport);
         }
      }

      public static class getName_call extends TAsyncMethodCall {
         public getName_call(AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, false);
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("getName", (byte)1, 0));
            getName_args args = new getName_args();
            args.write(prot);
            prot.writeMessageEnd();
         }

         public String getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
               return (new Client(prot)).recv_getName();
            }
         }
      }

      public static class getVersion_call extends TAsyncMethodCall {
         public getVersion_call(AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, false);
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("getVersion", (byte)1, 0));
            getVersion_args args = new getVersion_args();
            args.write(prot);
            prot.writeMessageEnd();
         }

         public String getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
               return (new Client(prot)).recv_getVersion();
            }
         }
      }

      public static class getStatus_call extends TAsyncMethodCall {
         public getStatus_call(AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, false);
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("getStatus", (byte)1, 0));
            getStatus_args args = new getStatus_args();
            args.write(prot);
            prot.writeMessageEnd();
         }

         public fb_status getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
               return (new Client(prot)).recv_getStatus();
            }
         }
      }

      public static class getStatusDetails_call extends TAsyncMethodCall {
         public getStatusDetails_call(AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, false);
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("getStatusDetails", (byte)1, 0));
            getStatusDetails_args args = new getStatusDetails_args();
            args.write(prot);
            prot.writeMessageEnd();
         }

         public String getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
               return (new Client(prot)).recv_getStatusDetails();
            }
         }
      }

      public static class getCounters_call extends TAsyncMethodCall {
         public getCounters_call(AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, false);
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("getCounters", (byte)1, 0));
            getCounters_args args = new getCounters_args();
            args.write(prot);
            prot.writeMessageEnd();
         }

         public Map getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
               return (new Client(prot)).recv_getCounters();
            }
         }
      }

      public static class getCounter_call extends TAsyncMethodCall {
         private String key;

         public getCounter_call(String key, AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, false);
            this.key = key;
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("getCounter", (byte)1, 0));
            getCounter_args args = new getCounter_args();
            args.setKey(this.key);
            args.write(prot);
            prot.writeMessageEnd();
         }

         public long getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
               return (new Client(prot)).recv_getCounter();
            }
         }
      }

      public static class setOption_call extends TAsyncMethodCall {
         private String key;
         private String value;

         public setOption_call(String key, String value, AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, false);
            this.key = key;
            this.value = value;
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("setOption", (byte)1, 0));
            setOption_args args = new setOption_args();
            args.setKey(this.key);
            args.setValue(this.value);
            args.write(prot);
            prot.writeMessageEnd();
         }

         public void getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
               (new Client(prot)).recv_setOption();
            }
         }
      }

      public static class getOption_call extends TAsyncMethodCall {
         private String key;

         public getOption_call(String key, AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, false);
            this.key = key;
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("getOption", (byte)1, 0));
            getOption_args args = new getOption_args();
            args.setKey(this.key);
            args.write(prot);
            prot.writeMessageEnd();
         }

         public String getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
               return (new Client(prot)).recv_getOption();
            }
         }
      }

      public static class getOptions_call extends TAsyncMethodCall {
         public getOptions_call(AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, false);
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("getOptions", (byte)1, 0));
            getOptions_args args = new getOptions_args();
            args.write(prot);
            prot.writeMessageEnd();
         }

         public Map getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
               return (new Client(prot)).recv_getOptions();
            }
         }
      }

      public static class getCpuProfile_call extends TAsyncMethodCall {
         private int profileDurationInSec;

         public getCpuProfile_call(int profileDurationInSec, AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, false);
            this.profileDurationInSec = profileDurationInSec;
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("getCpuProfile", (byte)1, 0));
            getCpuProfile_args args = new getCpuProfile_args();
            args.setProfileDurationInSec(this.profileDurationInSec);
            args.write(prot);
            prot.writeMessageEnd();
         }

         public String getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
               return (new Client(prot)).recv_getCpuProfile();
            }
         }
      }

      public static class aliveSince_call extends TAsyncMethodCall {
         public aliveSince_call(AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, false);
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("aliveSince", (byte)1, 0));
            aliveSince_args args = new aliveSince_args();
            args.write(prot);
            prot.writeMessageEnd();
         }

         public long getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
               return (new Client(prot)).recv_aliveSince();
            }
         }
      }

      public static class reinitialize_call extends TAsyncMethodCall {
         public reinitialize_call(AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, true);
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("reinitialize", (byte)4, 0));
            reinitialize_args args = new reinitialize_args();
            args.write(prot);
            prot.writeMessageEnd();
         }

         public void getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
            }
         }
      }

      public static class shutdown_call extends TAsyncMethodCall {
         public shutdown_call(AsyncMethodCallback resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
            super(client, protocolFactory, transport, resultHandler, true);
         }

         public void write_args(TProtocol prot) throws TException {
            prot.writeMessageBegin(new TMessage("shutdown", (byte)4, 0));
            shutdown_args args = new shutdown_args();
            args.write(prot);
            prot.writeMessageEnd();
         }

         public void getResult() throws TException {
            if (this.getState() != State.RESPONSE_READ) {
               throw new IllegalStateException("Method call not finished!");
            } else {
               TMemoryInputTransport memoryTransport = new TMemoryInputTransport(this.getFrameBuffer().array());
               TProtocol prot = this.client.getProtocolFactory().getProtocol(memoryTransport);
            }
         }
      }
   }

   public static class Processor extends TBaseProcessor implements TProcessor {
      private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class.getName());

      public Processor(Iface iface) {
         super(iface, getProcessMap(new HashMap()));
      }

      protected Processor(Iface iface, Map processMap) {
         super(iface, getProcessMap(processMap));
      }

      private static Map getProcessMap(Map processMap) {
         processMap.put("getName", new getName());
         processMap.put("getVersion", new getVersion());
         processMap.put("getStatus", new getStatus());
         processMap.put("getStatusDetails", new getStatusDetails());
         processMap.put("getCounters", new getCounters());
         processMap.put("getCounter", new getCounter());
         processMap.put("setOption", new setOption());
         processMap.put("getOption", new getOption());
         processMap.put("getOptions", new getOptions());
         processMap.put("getCpuProfile", new getCpuProfile());
         processMap.put("aliveSince", new aliveSince());
         processMap.put("reinitialize", new reinitialize());
         processMap.put("shutdown", new shutdown());
         return processMap;
      }

      public static class getName extends ProcessFunction {
         public getName() {
            super("getName");
         }

         public getName_args getEmptyArgsInstance() {
            return new getName_args();
         }

         protected boolean isOneway() {
            return false;
         }

         public getName_result getResult(Iface iface, getName_args args) throws TException {
            getName_result result = new getName_result();
            result.success = iface.getName();
            return result;
         }
      }

      public static class getVersion extends ProcessFunction {
         public getVersion() {
            super("getVersion");
         }

         public getVersion_args getEmptyArgsInstance() {
            return new getVersion_args();
         }

         protected boolean isOneway() {
            return false;
         }

         public getVersion_result getResult(Iface iface, getVersion_args args) throws TException {
            getVersion_result result = new getVersion_result();
            result.success = iface.getVersion();
            return result;
         }
      }

      public static class getStatus extends ProcessFunction {
         public getStatus() {
            super("getStatus");
         }

         public getStatus_args getEmptyArgsInstance() {
            return new getStatus_args();
         }

         protected boolean isOneway() {
            return false;
         }

         public getStatus_result getResult(Iface iface, getStatus_args args) throws TException {
            getStatus_result result = new getStatus_result();
            result.success = iface.getStatus();
            return result;
         }
      }

      public static class getStatusDetails extends ProcessFunction {
         public getStatusDetails() {
            super("getStatusDetails");
         }

         public getStatusDetails_args getEmptyArgsInstance() {
            return new getStatusDetails_args();
         }

         protected boolean isOneway() {
            return false;
         }

         public getStatusDetails_result getResult(Iface iface, getStatusDetails_args args) throws TException {
            getStatusDetails_result result = new getStatusDetails_result();
            result.success = iface.getStatusDetails();
            return result;
         }
      }

      public static class getCounters extends ProcessFunction {
         public getCounters() {
            super("getCounters");
         }

         public getCounters_args getEmptyArgsInstance() {
            return new getCounters_args();
         }

         protected boolean isOneway() {
            return false;
         }

         public getCounters_result getResult(Iface iface, getCounters_args args) throws TException {
            getCounters_result result = new getCounters_result();
            result.success = iface.getCounters();
            return result;
         }
      }

      public static class getCounter extends ProcessFunction {
         public getCounter() {
            super("getCounter");
         }

         public getCounter_args getEmptyArgsInstance() {
            return new getCounter_args();
         }

         protected boolean isOneway() {
            return false;
         }

         public getCounter_result getResult(Iface iface, getCounter_args args) throws TException {
            getCounter_result result = new getCounter_result();
            result.success = iface.getCounter(args.key);
            result.setSuccessIsSet(true);
            return result;
         }
      }

      public static class setOption extends ProcessFunction {
         public setOption() {
            super("setOption");
         }

         public setOption_args getEmptyArgsInstance() {
            return new setOption_args();
         }

         protected boolean isOneway() {
            return false;
         }

         public setOption_result getResult(Iface iface, setOption_args args) throws TException {
            setOption_result result = new setOption_result();
            iface.setOption(args.key, args.value);
            return result;
         }
      }

      public static class getOption extends ProcessFunction {
         public getOption() {
            super("getOption");
         }

         public getOption_args getEmptyArgsInstance() {
            return new getOption_args();
         }

         protected boolean isOneway() {
            return false;
         }

         public getOption_result getResult(Iface iface, getOption_args args) throws TException {
            getOption_result result = new getOption_result();
            result.success = iface.getOption(args.key);
            return result;
         }
      }

      public static class getOptions extends ProcessFunction {
         public getOptions() {
            super("getOptions");
         }

         public getOptions_args getEmptyArgsInstance() {
            return new getOptions_args();
         }

         protected boolean isOneway() {
            return false;
         }

         public getOptions_result getResult(Iface iface, getOptions_args args) throws TException {
            getOptions_result result = new getOptions_result();
            result.success = iface.getOptions();
            return result;
         }
      }

      public static class getCpuProfile extends ProcessFunction {
         public getCpuProfile() {
            super("getCpuProfile");
         }

         public getCpuProfile_args getEmptyArgsInstance() {
            return new getCpuProfile_args();
         }

         protected boolean isOneway() {
            return false;
         }

         public getCpuProfile_result getResult(Iface iface, getCpuProfile_args args) throws TException {
            getCpuProfile_result result = new getCpuProfile_result();
            result.success = iface.getCpuProfile(args.profileDurationInSec);
            return result;
         }
      }

      public static class aliveSince extends ProcessFunction {
         public aliveSince() {
            super("aliveSince");
         }

         public aliveSince_args getEmptyArgsInstance() {
            return new aliveSince_args();
         }

         protected boolean isOneway() {
            return false;
         }

         public aliveSince_result getResult(Iface iface, aliveSince_args args) throws TException {
            aliveSince_result result = new aliveSince_result();
            result.success = iface.aliveSince();
            result.setSuccessIsSet(true);
            return result;
         }
      }

      public static class reinitialize extends ProcessFunction {
         public reinitialize() {
            super("reinitialize");
         }

         public reinitialize_args getEmptyArgsInstance() {
            return new reinitialize_args();
         }

         protected boolean isOneway() {
            return true;
         }

         public TBase getResult(Iface iface, reinitialize_args args) throws TException {
            iface.reinitialize();
            return null;
         }
      }

      public static class shutdown extends ProcessFunction {
         public shutdown() {
            super("shutdown");
         }

         public shutdown_args getEmptyArgsInstance() {
            return new shutdown_args();
         }

         protected boolean isOneway() {
            return true;
         }

         public TBase getResult(Iface iface, shutdown_args args) throws TException {
            iface.shutdown();
            return null;
         }
      }
   }

   public static class AsyncProcessor extends TBaseAsyncProcessor {
      private static final Logger LOGGER = LoggerFactory.getLogger(AsyncProcessor.class.getName());

      public AsyncProcessor(AsyncIface iface) {
         super(iface, getProcessMap(new HashMap()));
      }

      protected AsyncProcessor(AsyncIface iface, Map processMap) {
         super(iface, getProcessMap(processMap));
      }

      private static Map getProcessMap(Map processMap) {
         processMap.put("getName", new getName());
         processMap.put("getVersion", new getVersion());
         processMap.put("getStatus", new getStatus());
         processMap.put("getStatusDetails", new getStatusDetails());
         processMap.put("getCounters", new getCounters());
         processMap.put("getCounter", new getCounter());
         processMap.put("setOption", new setOption());
         processMap.put("getOption", new getOption());
         processMap.put("getOptions", new getOptions());
         processMap.put("getCpuProfile", new getCpuProfile());
         processMap.put("aliveSince", new aliveSince());
         processMap.put("reinitialize", new reinitialize());
         processMap.put("shutdown", new shutdown());
         return processMap;
      }

      // $FF: synthetic method
      static Logger access$000() {
         return LOGGER;
      }

      public static class getName extends AsyncProcessFunction {
         public getName() {
            super("getName");
         }

         public getName_args getEmptyArgsInstance() {
            return new getName_args();
         }

         public AsyncMethodCallback getResultHandler(AbstractNonblockingServer.AsyncFrameBuffer fb, int seqid) {
            // $FF: Couldn't be decompiled
         }

         protected boolean isOneway() {
            return false;
         }

         public void start(AsyncIface iface, getName_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.getName(resultHandler);
         }
      }

      public static class getVersion extends AsyncProcessFunction {
         public getVersion() {
            super("getVersion");
         }

         public getVersion_args getEmptyArgsInstance() {
            return new getVersion_args();
         }

         public AsyncMethodCallback getResultHandler(AbstractNonblockingServer.AsyncFrameBuffer fb, int seqid) {
            // $FF: Couldn't be decompiled
         }

         protected boolean isOneway() {
            return false;
         }

         public void start(AsyncIface iface, getVersion_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.getVersion(resultHandler);
         }
      }

      public static class getStatus extends AsyncProcessFunction {
         public getStatus() {
            super("getStatus");
         }

         public getStatus_args getEmptyArgsInstance() {
            return new getStatus_args();
         }

         public AsyncMethodCallback getResultHandler(AbstractNonblockingServer.AsyncFrameBuffer fb, int seqid) {
            // $FF: Couldn't be decompiled
         }

         protected boolean isOneway() {
            return false;
         }

         public void start(AsyncIface iface, getStatus_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.getStatus(resultHandler);
         }
      }

      public static class getStatusDetails extends AsyncProcessFunction {
         public getStatusDetails() {
            super("getStatusDetails");
         }

         public getStatusDetails_args getEmptyArgsInstance() {
            return new getStatusDetails_args();
         }

         public AsyncMethodCallback getResultHandler(AbstractNonblockingServer.AsyncFrameBuffer fb, int seqid) {
            // $FF: Couldn't be decompiled
         }

         protected boolean isOneway() {
            return false;
         }

         public void start(AsyncIface iface, getStatusDetails_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.getStatusDetails(resultHandler);
         }
      }

      public static class getCounters extends AsyncProcessFunction {
         public getCounters() {
            super("getCounters");
         }

         public getCounters_args getEmptyArgsInstance() {
            return new getCounters_args();
         }

         public AsyncMethodCallback getResultHandler(AbstractNonblockingServer.AsyncFrameBuffer fb, int seqid) {
            // $FF: Couldn't be decompiled
         }

         protected boolean isOneway() {
            return false;
         }

         public void start(AsyncIface iface, getCounters_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.getCounters(resultHandler);
         }
      }

      public static class getCounter extends AsyncProcessFunction {
         public getCounter() {
            super("getCounter");
         }

         public getCounter_args getEmptyArgsInstance() {
            return new getCounter_args();
         }

         public AsyncMethodCallback getResultHandler(AbstractNonblockingServer.AsyncFrameBuffer fb, int seqid) {
            // $FF: Couldn't be decompiled
         }

         protected boolean isOneway() {
            return false;
         }

         public void start(AsyncIface iface, getCounter_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.getCounter(args.key, resultHandler);
         }
      }

      public static class setOption extends AsyncProcessFunction {
         public setOption() {
            super("setOption");
         }

         public setOption_args getEmptyArgsInstance() {
            return new setOption_args();
         }

         public AsyncMethodCallback getResultHandler(AbstractNonblockingServer.AsyncFrameBuffer fb, int seqid) {
            // $FF: Couldn't be decompiled
         }

         protected boolean isOneway() {
            return false;
         }

         public void start(AsyncIface iface, setOption_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.setOption(args.key, args.value, resultHandler);
         }
      }

      public static class getOption extends AsyncProcessFunction {
         public getOption() {
            super("getOption");
         }

         public getOption_args getEmptyArgsInstance() {
            return new getOption_args();
         }

         public AsyncMethodCallback getResultHandler(AbstractNonblockingServer.AsyncFrameBuffer fb, int seqid) {
            // $FF: Couldn't be decompiled
         }

         protected boolean isOneway() {
            return false;
         }

         public void start(AsyncIface iface, getOption_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.getOption(args.key, resultHandler);
         }
      }

      public static class getOptions extends AsyncProcessFunction {
         public getOptions() {
            super("getOptions");
         }

         public getOptions_args getEmptyArgsInstance() {
            return new getOptions_args();
         }

         public AsyncMethodCallback getResultHandler(AbstractNonblockingServer.AsyncFrameBuffer fb, int seqid) {
            // $FF: Couldn't be decompiled
         }

         protected boolean isOneway() {
            return false;
         }

         public void start(AsyncIface iface, getOptions_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.getOptions(resultHandler);
         }
      }

      public static class getCpuProfile extends AsyncProcessFunction {
         public getCpuProfile() {
            super("getCpuProfile");
         }

         public getCpuProfile_args getEmptyArgsInstance() {
            return new getCpuProfile_args();
         }

         public AsyncMethodCallback getResultHandler(AbstractNonblockingServer.AsyncFrameBuffer fb, int seqid) {
            // $FF: Couldn't be decompiled
         }

         protected boolean isOneway() {
            return false;
         }

         public void start(AsyncIface iface, getCpuProfile_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.getCpuProfile(args.profileDurationInSec, resultHandler);
         }
      }

      public static class aliveSince extends AsyncProcessFunction {
         public aliveSince() {
            super("aliveSince");
         }

         public aliveSince_args getEmptyArgsInstance() {
            return new aliveSince_args();
         }

         public AsyncMethodCallback getResultHandler(AbstractNonblockingServer.AsyncFrameBuffer fb, int seqid) {
            // $FF: Couldn't be decompiled
         }

         protected boolean isOneway() {
            return false;
         }

         public void start(AsyncIface iface, aliveSince_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.aliveSince(resultHandler);
         }
      }

      public static class reinitialize extends AsyncProcessFunction {
         public reinitialize() {
            super("reinitialize");
         }

         public reinitialize_args getEmptyArgsInstance() {
            return new reinitialize_args();
         }

         public AsyncMethodCallback getResultHandler(AbstractNonblockingServer.AsyncFrameBuffer fb, int seqid) {
            // $FF: Couldn't be decompiled
         }

         protected boolean isOneway() {
            return true;
         }

         public void start(AsyncIface iface, reinitialize_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.reinitialize(resultHandler);
         }
      }

      public static class shutdown extends AsyncProcessFunction {
         public shutdown() {
            super("shutdown");
         }

         public shutdown_args getEmptyArgsInstance() {
            return new shutdown_args();
         }

         public AsyncMethodCallback getResultHandler(AbstractNonblockingServer.AsyncFrameBuffer fb, int seqid) {
            // $FF: Couldn't be decompiled
         }

         protected boolean isOneway() {
            return true;
         }

         public void start(AsyncIface iface, shutdown_args args, AsyncMethodCallback resultHandler) throws TException {
            iface.shutdown(resultHandler);
         }
      }
   }

   public static class getName_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("getName_args");
      private static final Map schemes = new HashMap();
      public static final Map metaDataMap;

      public getName_args() {
      }

      public getName_args(getName_args other) {
      }

      public getName_args deepCopy() {
         return new getName_args(this);
      }

      public void clear() {
      }

      public void setFieldValue(_Fields field, Object value) {
         int var10000 = null.$SwitchMap$com$facebook$fb303$FacebookService$getName_args$_Fields[field.ordinal()];
      }

      public Object getFieldValue(_Fields field) {
         int var10000 = null.$SwitchMap$com$facebook$fb303$FacebookService$getName_args$_Fields[field.ordinal()];
         throw new IllegalStateException();
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            int var10000 = null.$SwitchMap$com$facebook$fb303$FacebookService$getName_args$_Fields[field.ordinal()];
            throw new IllegalStateException();
         }
      }

      public boolean equals(Object that) {
         if (that == null) {
            return false;
         } else {
            return that instanceof getName_args ? this.equals((getName_args)that) : false;
         }
      }

      public boolean equals(getName_args that) {
         return that != null;
      }

      public int hashCode() {
         List<Object> list = new ArrayList();
         return list.hashCode();
      }

      public int compareTo(getName_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            return 0;
         }
      }

      public _Fields fieldForId(int fieldId) {
         return FacebookService.getName_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         ((SchemeFactory)schemes.get(iprot.getScheme())).getScheme().read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         ((SchemeFactory)schemes.get(oprot.getScheme())).getScheme().write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("getName_args(");
         boolean first = true;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      static {
         schemes.put(StandardScheme.class, new getName_argsStandardSchemeFactory());
         schemes.put(TupleScheme.class, new getName_argsTupleSchemeFactory());
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(getName_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class getName_argsStandardSchemeFactory implements SchemeFactory {
         private getName_argsStandardSchemeFactory() {
         }

         public getName_argsStandardScheme getScheme() {
            return new getName_argsStandardScheme();
         }
      }

      private static class getName_argsStandardScheme extends StandardScheme {
         private getName_argsStandardScheme() {
         }

         public void read(TProtocol iprot, getName_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
                     iprot.readFieldEnd();
               }
            }
         }

         public void write(TProtocol oprot, getName_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(FacebookService.getName_args.STRUCT_DESC);
            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class getName_argsTupleSchemeFactory implements SchemeFactory {
         private getName_argsTupleSchemeFactory() {
         }

         public getName_argsTupleScheme getScheme() {
            return new getName_argsTupleScheme();
         }
      }

      private static class getName_argsTupleScheme extends TupleScheme {
         private getName_argsTupleScheme() {
         }

         public void write(TProtocol prot, getName_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
         }

         public void read(TProtocol prot, getName_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
         }
      }
   }

   public static class getName_result implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("getName_result");
      private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte)11, (short)0);
      private static final Map schemes = new HashMap();
      public String success;
      public static final Map metaDataMap;

      public getName_result() {
      }

      public getName_result(String success) {
         this();
         this.success = success;
      }

      public getName_result(getName_result other) {
         if (other.isSetSuccess()) {
            this.success = other.success;
         }

      }

      public getName_result deepCopy() {
         return new getName_result(this);
      }

      public void clear() {
         this.success = null;
      }

      public String getSuccess() {
         return this.success;
      }

      public getName_result setSuccess(String success) {
         this.success = success;
         return this;
      }

      public void unsetSuccess() {
         this.success = null;
      }

      public boolean isSetSuccess() {
         return this.success != null;
      }

      public void setSuccessIsSet(boolean value) {
         if (!value) {
            this.success = null;
         }

      }

      public void setFieldValue(_Fields field, Object value) {
         switch (field) {
            case SUCCESS:
               if (value == null) {
                  this.unsetSuccess();
               } else {
                  this.setSuccess((String)value);
               }
            default:
         }
      }

      public Object getFieldValue(_Fields field) {
         switch (field) {
            case SUCCESS:
               return this.getSuccess();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case SUCCESS:
                  return this.isSetSuccess();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         if (that == null) {
            return false;
         } else {
            return that instanceof getName_result ? this.equals((getName_result)that) : false;
         }
      }

      public boolean equals(getName_result that) {
         if (that == null) {
            return false;
         } else {
            boolean this_present_success = this.isSetSuccess();
            boolean that_present_success = that.isSetSuccess();
            if (this_present_success || that_present_success) {
               if (!this_present_success || !that_present_success) {
                  return false;
               }

               if (!this.success.equals(that.success)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         List<Object> list = new ArrayList();
         boolean present_success = this.isSetSuccess();
         list.add(present_success);
         if (present_success) {
            list.add(this.success);
         }

         return list.hashCode();
      }

      public int compareTo(getName_result other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.valueOf(this.isSetSuccess()).compareTo(other.isSetSuccess());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSuccess()) {
                  lastComparison = TBaseHelper.compareTo(this.success, other.success);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      public _Fields fieldForId(int fieldId) {
         return FacebookService.getName_result._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         ((SchemeFactory)schemes.get(iprot.getScheme())).getScheme().read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         ((SchemeFactory)schemes.get(oprot.getScheme())).getScheme().write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("getName_result(");
         boolean first = true;
         sb.append("success:");
         if (this.success == null) {
            sb.append("null");
         } else {
            sb.append(this.success);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      static {
         schemes.put(StandardScheme.class, new getName_resultStandardSchemeFactory());
         schemes.put(TupleScheme.class, new getName_resultTupleSchemeFactory());
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(FacebookService.getName_result._Fields.SUCCESS, new FieldMetaData("success", (byte)3, new FieldValueMetaData((byte)11)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(getName_result.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         SUCCESS((short)0, "success");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 0:
                  return SUCCESS;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class getName_resultStandardSchemeFactory implements SchemeFactory {
         private getName_resultStandardSchemeFactory() {
         }

         public getName_resultStandardScheme getScheme() {
            return new getName_resultStandardScheme();
         }
      }

      private static class getName_resultStandardScheme extends StandardScheme {
         private getName_resultStandardScheme() {
         }

         public void read(TProtocol iprot, getName_result struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 0:
                     if (schemeField.type == 11) {
                        struct.success = iprot.readString();
                        struct.setSuccessIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, getName_result struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(FacebookService.getName_result.STRUCT_DESC);
            if (struct.success != null) {
               oprot.writeFieldBegin(FacebookService.getName_result.SUCCESS_FIELD_DESC);
               oprot.writeString(struct.success);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class getName_resultTupleSchemeFactory implements SchemeFactory {
         private getName_resultTupleSchemeFactory() {
         }

         public getName_resultTupleScheme getScheme() {
            return new getName_resultTupleScheme();
         }
      }

      private static class getName_resultTupleScheme extends TupleScheme {
         private getName_resultTupleScheme() {
         }

         public void write(TProtocol prot, getName_result struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetSuccess()) {
               oprot.writeString(struct.success);
            }

         }

         public void read(TProtocol prot, getName_result struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.success = iprot.readString();
               struct.setSuccessIsSet(true);
            }

         }
      }
   }

   public static class getVersion_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("getVersion_args");
      private static final Map schemes = new HashMap();
      public static final Map metaDataMap;

      public getVersion_args() {
      }

      public getVersion_args(getVersion_args other) {
      }

      public getVersion_args deepCopy() {
         return new getVersion_args(this);
      }

      public void clear() {
      }

      public void setFieldValue(_Fields field, Object value) {
         int var10000 = null.$SwitchMap$com$facebook$fb303$FacebookService$getVersion_args$_Fields[field.ordinal()];
      }

      public Object getFieldValue(_Fields field) {
         int var10000 = null.$SwitchMap$com$facebook$fb303$FacebookService$getVersion_args$_Fields[field.ordinal()];
         throw new IllegalStateException();
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            int var10000 = null.$SwitchMap$com$facebook$fb303$FacebookService$getVersion_args$_Fields[field.ordinal()];
            throw new IllegalStateException();
         }
      }

      public boolean equals(Object that) {
         if (that == null) {
            return false;
         } else {
            return that instanceof getVersion_args ? this.equals((getVersion_args)that) : false;
         }
      }

      public boolean equals(getVersion_args that) {
         return that != null;
      }

      public int hashCode() {
         List<Object> list = new ArrayList();
         return list.hashCode();
      }

      public int compareTo(getVersion_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            return 0;
         }
      }

      public _Fields fieldForId(int fieldId) {
         return FacebookService.getVersion_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         ((SchemeFactory)schemes.get(iprot.getScheme())).getScheme().read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         ((SchemeFactory)schemes.get(oprot.getScheme())).getScheme().write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("getVersion_args(");
         boolean first = true;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      static {
         schemes.put(StandardScheme.class, new getVersion_argsStandardSchemeFactory());
         schemes.put(TupleScheme.class, new getVersion_argsTupleSchemeFactory());
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(getVersion_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class getVersion_argsStandardSchemeFactory implements SchemeFactory {
         private getVersion_argsStandardSchemeFactory() {
         }

         public getVersion_argsStandardScheme getScheme() {
            return new getVersion_argsStandardScheme();
         }
      }

      private static class getVersion_argsStandardScheme extends StandardScheme {
         private getVersion_argsStandardScheme() {
         }

         public void read(TProtocol iprot, getVersion_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
                     iprot.readFieldEnd();
               }
            }
         }

         public void write(TProtocol oprot, getVersion_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(FacebookService.getVersion_args.STRUCT_DESC);
            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class getVersion_argsTupleSchemeFactory implements SchemeFactory {
         private getVersion_argsTupleSchemeFactory() {
         }

         public getVersion_argsTupleScheme getScheme() {
            return new getVersion_argsTupleScheme();
         }
      }

      private static class getVersion_argsTupleScheme extends TupleScheme {
         private getVersion_argsTupleScheme() {
         }

         public void write(TProtocol prot, getVersion_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
         }

         public void read(TProtocol prot, getVersion_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
         }
      }
   }

   public static class getVersion_result implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("getVersion_result");
      private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte)11, (short)0);
      private static final Map schemes = new HashMap();
      public String success;
      public static final Map metaDataMap;

      public getVersion_result() {
      }

      public getVersion_result(String success) {
         this();
         this.success = success;
      }

      public getVersion_result(getVersion_result other) {
         if (other.isSetSuccess()) {
            this.success = other.success;
         }

      }

      public getVersion_result deepCopy() {
         return new getVersion_result(this);
      }

      public void clear() {
         this.success = null;
      }

      public String getSuccess() {
         return this.success;
      }

      public getVersion_result setSuccess(String success) {
         this.success = success;
         return this;
      }

      public void unsetSuccess() {
         this.success = null;
      }

      public boolean isSetSuccess() {
         return this.success != null;
      }

      public void setSuccessIsSet(boolean value) {
         if (!value) {
            this.success = null;
         }

      }

      public void setFieldValue(_Fields field, Object value) {
         switch (field) {
            case SUCCESS:
               if (value == null) {
                  this.unsetSuccess();
               } else {
                  this.setSuccess((String)value);
               }
            default:
         }
      }

      public Object getFieldValue(_Fields field) {
         switch (field) {
            case SUCCESS:
               return this.getSuccess();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case SUCCESS:
                  return this.isSetSuccess();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         if (that == null) {
            return false;
         } else {
            return that instanceof getVersion_result ? this.equals((getVersion_result)that) : false;
         }
      }

      public boolean equals(getVersion_result that) {
         if (that == null) {
            return false;
         } else {
            boolean this_present_success = this.isSetSuccess();
            boolean that_present_success = that.isSetSuccess();
            if (this_present_success || that_present_success) {
               if (!this_present_success || !that_present_success) {
                  return false;
               }

               if (!this.success.equals(that.success)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         List<Object> list = new ArrayList();
         boolean present_success = this.isSetSuccess();
         list.add(present_success);
         if (present_success) {
            list.add(this.success);
         }

         return list.hashCode();
      }

      public int compareTo(getVersion_result other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.valueOf(this.isSetSuccess()).compareTo(other.isSetSuccess());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSuccess()) {
                  lastComparison = TBaseHelper.compareTo(this.success, other.success);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      public _Fields fieldForId(int fieldId) {
         return FacebookService.getVersion_result._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         ((SchemeFactory)schemes.get(iprot.getScheme())).getScheme().read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         ((SchemeFactory)schemes.get(oprot.getScheme())).getScheme().write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("getVersion_result(");
         boolean first = true;
         sb.append("success:");
         if (this.success == null) {
            sb.append("null");
         } else {
            sb.append(this.success);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      static {
         schemes.put(StandardScheme.class, new getVersion_resultStandardSchemeFactory());
         schemes.put(TupleScheme.class, new getVersion_resultTupleSchemeFactory());
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(FacebookService.getVersion_result._Fields.SUCCESS, new FieldMetaData("success", (byte)3, new FieldValueMetaData((byte)11)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(getVersion_result.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         SUCCESS((short)0, "success");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 0:
                  return SUCCESS;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class getVersion_resultStandardSchemeFactory implements SchemeFactory {
         private getVersion_resultStandardSchemeFactory() {
         }

         public getVersion_resultStandardScheme getScheme() {
            return new getVersion_resultStandardScheme();
         }
      }

      private static class getVersion_resultStandardScheme extends StandardScheme {
         private getVersion_resultStandardScheme() {
         }

         public void read(TProtocol iprot, getVersion_result struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 0:
                     if (schemeField.type == 11) {
                        struct.success = iprot.readString();
                        struct.setSuccessIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, getVersion_result struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(FacebookService.getVersion_result.STRUCT_DESC);
            if (struct.success != null) {
               oprot.writeFieldBegin(FacebookService.getVersion_result.SUCCESS_FIELD_DESC);
               oprot.writeString(struct.success);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class getVersion_resultTupleSchemeFactory implements SchemeFactory {
         private getVersion_resultTupleSchemeFactory() {
         }

         public getVersion_resultTupleScheme getScheme() {
            return new getVersion_resultTupleScheme();
         }
      }

      private static class getVersion_resultTupleScheme extends TupleScheme {
         private getVersion_resultTupleScheme() {
         }

         public void write(TProtocol prot, getVersion_result struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetSuccess()) {
               oprot.writeString(struct.success);
            }

         }

         public void read(TProtocol prot, getVersion_result struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.success = iprot.readString();
               struct.setSuccessIsSet(true);
            }

         }
      }
   }

   public static class getStatus_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("getStatus_args");
      private static final Map schemes = new HashMap();
      public static final Map metaDataMap;

      public getStatus_args() {
      }

      public getStatus_args(getStatus_args other) {
      }

      public getStatus_args deepCopy() {
         return new getStatus_args(this);
      }

      public void clear() {
      }

      public void setFieldValue(_Fields field, Object value) {
         int var10000 = null.$SwitchMap$com$facebook$fb303$FacebookService$getStatus_args$_Fields[field.ordinal()];
      }

      public Object getFieldValue(_Fields field) {
         int var10000 = null.$SwitchMap$com$facebook$fb303$FacebookService$getStatus_args$_Fields[field.ordinal()];
         throw new IllegalStateException();
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            int var10000 = null.$SwitchMap$com$facebook$fb303$FacebookService$getStatus_args$_Fields[field.ordinal()];
            throw new IllegalStateException();
         }
      }

      public boolean equals(Object that) {
         if (that == null) {
            return false;
         } else {
            return that instanceof getStatus_args ? this.equals((getStatus_args)that) : false;
         }
      }

      public boolean equals(getStatus_args that) {
         return that != null;
      }

      public int hashCode() {
         List<Object> list = new ArrayList();
         return list.hashCode();
      }

      public int compareTo(getStatus_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            return 0;
         }
      }

      public _Fields fieldForId(int fieldId) {
         return FacebookService.getStatus_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         ((SchemeFactory)schemes.get(iprot.getScheme())).getScheme().read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         ((SchemeFactory)schemes.get(oprot.getScheme())).getScheme().write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("getStatus_args(");
         boolean first = true;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      static {
         schemes.put(StandardScheme.class, new getStatus_argsStandardSchemeFactory());
         schemes.put(TupleScheme.class, new getStatus_argsTupleSchemeFactory());
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(getStatus_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class getStatus_argsStandardSchemeFactory implements SchemeFactory {
         private getStatus_argsStandardSchemeFactory() {
         }

         public getStatus_argsStandardScheme getScheme() {
            return new getStatus_argsStandardScheme();
         }
      }

      private static class getStatus_argsStandardScheme extends StandardScheme {
         private getStatus_argsStandardScheme() {
         }

         public void read(TProtocol iprot, getStatus_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
                     iprot.readFieldEnd();
               }
            }
         }

         public void write(TProtocol oprot, getStatus_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(FacebookService.getStatus_args.STRUCT_DESC);
            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class getStatus_argsTupleSchemeFactory implements SchemeFactory {
         private getStatus_argsTupleSchemeFactory() {
         }

         public getStatus_argsTupleScheme getScheme() {
            return new getStatus_argsTupleScheme();
         }
      }

      private static class getStatus_argsTupleScheme extends TupleScheme {
         private getStatus_argsTupleScheme() {
         }

         public void write(TProtocol prot, getStatus_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
         }

         public void read(TProtocol prot, getStatus_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
         }
      }
   }

   public static class getStatus_result implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("getStatus_result");
      private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte)8, (short)0);
      private static final Map schemes = new HashMap();
      public fb_status success;
      public static final Map metaDataMap;

      public getStatus_result() {
      }

      public getStatus_result(fb_status success) {
         this();
         this.success = success;
      }

      public getStatus_result(getStatus_result other) {
         if (other.isSetSuccess()) {
            this.success = other.success;
         }

      }

      public getStatus_result deepCopy() {
         return new getStatus_result(this);
      }

      public void clear() {
         this.success = null;
      }

      public fb_status getSuccess() {
         return this.success;
      }

      public getStatus_result setSuccess(fb_status success) {
         this.success = success;
         return this;
      }

      public void unsetSuccess() {
         this.success = null;
      }

      public boolean isSetSuccess() {
         return this.success != null;
      }

      public void setSuccessIsSet(boolean value) {
         if (!value) {
            this.success = null;
         }

      }

      public void setFieldValue(_Fields field, Object value) {
         switch (field) {
            case SUCCESS:
               if (value == null) {
                  this.unsetSuccess();
               } else {
                  this.setSuccess((fb_status)value);
               }
            default:
         }
      }

      public Object getFieldValue(_Fields field) {
         switch (field) {
            case SUCCESS:
               return this.getSuccess();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case SUCCESS:
                  return this.isSetSuccess();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         if (that == null) {
            return false;
         } else {
            return that instanceof getStatus_result ? this.equals((getStatus_result)that) : false;
         }
      }

      public boolean equals(getStatus_result that) {
         if (that == null) {
            return false;
         } else {
            boolean this_present_success = this.isSetSuccess();
            boolean that_present_success = that.isSetSuccess();
            if (this_present_success || that_present_success) {
               if (!this_present_success || !that_present_success) {
                  return false;
               }

               if (!this.success.equals(that.success)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         List<Object> list = new ArrayList();
         boolean present_success = this.isSetSuccess();
         list.add(present_success);
         if (present_success) {
            list.add(this.success.getValue());
         }

         return list.hashCode();
      }

      public int compareTo(getStatus_result other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.valueOf(this.isSetSuccess()).compareTo(other.isSetSuccess());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSuccess()) {
                  lastComparison = TBaseHelper.compareTo(this.success, other.success);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      public _Fields fieldForId(int fieldId) {
         return FacebookService.getStatus_result._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         ((SchemeFactory)schemes.get(iprot.getScheme())).getScheme().read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         ((SchemeFactory)schemes.get(oprot.getScheme())).getScheme().write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("getStatus_result(");
         boolean first = true;
         sb.append("success:");
         if (this.success == null) {
            sb.append("null");
         } else {
            sb.append(this.success);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      static {
         schemes.put(StandardScheme.class, new getStatus_resultStandardSchemeFactory());
         schemes.put(TupleScheme.class, new getStatus_resultTupleSchemeFactory());
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(FacebookService.getStatus_result._Fields.SUCCESS, new FieldMetaData("success", (byte)3, new EnumMetaData((byte)16, fb_status.class)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(getStatus_result.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         SUCCESS((short)0, "success");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 0:
                  return SUCCESS;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class getStatus_resultStandardSchemeFactory implements SchemeFactory {
         private getStatus_resultStandardSchemeFactory() {
         }

         public getStatus_resultStandardScheme getScheme() {
            return new getStatus_resultStandardScheme();
         }
      }

      private static class getStatus_resultStandardScheme extends StandardScheme {
         private getStatus_resultStandardScheme() {
         }

         public void read(TProtocol iprot, getStatus_result struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 0:
                     if (schemeField.type == 8) {
                        struct.success = fb_status.findByValue(iprot.readI32());
                        struct.setSuccessIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, getStatus_result struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(FacebookService.getStatus_result.STRUCT_DESC);
            if (struct.success != null) {
               oprot.writeFieldBegin(FacebookService.getStatus_result.SUCCESS_FIELD_DESC);
               oprot.writeI32(struct.success.getValue());
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class getStatus_resultTupleSchemeFactory implements SchemeFactory {
         private getStatus_resultTupleSchemeFactory() {
         }

         public getStatus_resultTupleScheme getScheme() {
            return new getStatus_resultTupleScheme();
         }
      }

      private static class getStatus_resultTupleScheme extends TupleScheme {
         private getStatus_resultTupleScheme() {
         }

         public void write(TProtocol prot, getStatus_result struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetSuccess()) {
               oprot.writeI32(struct.success.getValue());
            }

         }

         public void read(TProtocol prot, getStatus_result struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.success = fb_status.findByValue(iprot.readI32());
               struct.setSuccessIsSet(true);
            }

         }
      }
   }

   public static class getStatusDetails_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("getStatusDetails_args");
      private static final Map schemes = new HashMap();
      public static final Map metaDataMap;

      public getStatusDetails_args() {
      }

      public getStatusDetails_args(getStatusDetails_args other) {
      }

      public getStatusDetails_args deepCopy() {
         return new getStatusDetails_args(this);
      }

      public void clear() {
      }

      public void setFieldValue(_Fields field, Object value) {
         int var10000 = null.$SwitchMap$com$facebook$fb303$FacebookService$getStatusDetails_args$_Fields[field.ordinal()];
      }

      public Object getFieldValue(_Fields field) {
         int var10000 = null.$SwitchMap$com$facebook$fb303$FacebookService$getStatusDetails_args$_Fields[field.ordinal()];
         throw new IllegalStateException();
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            int var10000 = null.$SwitchMap$com$facebook$fb303$FacebookService$getStatusDetails_args$_Fields[field.ordinal()];
            throw new IllegalStateException();
         }
      }

      public boolean equals(Object that) {
         if (that == null) {
            return false;
         } else {
            return that instanceof getStatusDetails_args ? this.equals((getStatusDetails_args)that) : false;
         }
      }

      public boolean equals(getStatusDetails_args that) {
         return that != null;
      }

      public int hashCode() {
         List<Object> list = new ArrayList();
         return list.hashCode();
      }

      public int compareTo(getStatusDetails_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            return 0;
         }
      }

      public _Fields fieldForId(int fieldId) {
         return FacebookService.getStatusDetails_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         ((SchemeFactory)schemes.get(iprot.getScheme())).getScheme().read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         ((SchemeFactory)schemes.get(oprot.getScheme())).getScheme().write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("getStatusDetails_args(");
         boolean first = true;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      static {
         schemes.put(StandardScheme.class, new getStatusDetails_argsStandardSchemeFactory());
         schemes.put(TupleScheme.class, new getStatusDetails_argsTupleSchemeFactory());
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(getStatusDetails_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class getStatusDetails_argsStandardSchemeFactory implements SchemeFactory {
         private getStatusDetails_argsStandardSchemeFactory() {
         }

         public getStatusDetails_argsStandardScheme getScheme() {
            return new getStatusDetails_argsStandardScheme();
         }
      }

      private static class getStatusDetails_argsStandardScheme extends StandardScheme {
         private getStatusDetails_argsStandardScheme() {
         }

         public void read(TProtocol iprot, getStatusDetails_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
                     iprot.readFieldEnd();
               }
            }
         }

         public void write(TProtocol oprot, getStatusDetails_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(FacebookService.getStatusDetails_args.STRUCT_DESC);
            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class getStatusDetails_argsTupleSchemeFactory implements SchemeFactory {
         private getStatusDetails_argsTupleSchemeFactory() {
         }

         public getStatusDetails_argsTupleScheme getScheme() {
            return new getStatusDetails_argsTupleScheme();
         }
      }

      private static class getStatusDetails_argsTupleScheme extends TupleScheme {
         private getStatusDetails_argsTupleScheme() {
         }

         public void write(TProtocol prot, getStatusDetails_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
         }

         public void read(TProtocol prot, getStatusDetails_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
         }
      }
   }

   public static class getStatusDetails_result implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("getStatusDetails_result");
      private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte)11, (short)0);
      private static final Map schemes = new HashMap();
      public String success;
      public static final Map metaDataMap;

      public getStatusDetails_result() {
      }

      public getStatusDetails_result(String success) {
         this();
         this.success = success;
      }

      public getStatusDetails_result(getStatusDetails_result other) {
         if (other.isSetSuccess()) {
            this.success = other.success;
         }

      }

      public getStatusDetails_result deepCopy() {
         return new getStatusDetails_result(this);
      }

      public void clear() {
         this.success = null;
      }

      public String getSuccess() {
         return this.success;
      }

      public getStatusDetails_result setSuccess(String success) {
         this.success = success;
         return this;
      }

      public void unsetSuccess() {
         this.success = null;
      }

      public boolean isSetSuccess() {
         return this.success != null;
      }

      public void setSuccessIsSet(boolean value) {
         if (!value) {
            this.success = null;
         }

      }

      public void setFieldValue(_Fields field, Object value) {
         switch (field) {
            case SUCCESS:
               if (value == null) {
                  this.unsetSuccess();
               } else {
                  this.setSuccess((String)value);
               }
            default:
         }
      }

      public Object getFieldValue(_Fields field) {
         switch (field) {
            case SUCCESS:
               return this.getSuccess();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case SUCCESS:
                  return this.isSetSuccess();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         if (that == null) {
            return false;
         } else {
            return that instanceof getStatusDetails_result ? this.equals((getStatusDetails_result)that) : false;
         }
      }

      public boolean equals(getStatusDetails_result that) {
         if (that == null) {
            return false;
         } else {
            boolean this_present_success = this.isSetSuccess();
            boolean that_present_success = that.isSetSuccess();
            if (this_present_success || that_present_success) {
               if (!this_present_success || !that_present_success) {
                  return false;
               }

               if (!this.success.equals(that.success)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         List<Object> list = new ArrayList();
         boolean present_success = this.isSetSuccess();
         list.add(present_success);
         if (present_success) {
            list.add(this.success);
         }

         return list.hashCode();
      }

      public int compareTo(getStatusDetails_result other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.valueOf(this.isSetSuccess()).compareTo(other.isSetSuccess());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSuccess()) {
                  lastComparison = TBaseHelper.compareTo(this.success, other.success);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      public _Fields fieldForId(int fieldId) {
         return FacebookService.getStatusDetails_result._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         ((SchemeFactory)schemes.get(iprot.getScheme())).getScheme().read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         ((SchemeFactory)schemes.get(oprot.getScheme())).getScheme().write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("getStatusDetails_result(");
         boolean first = true;
         sb.append("success:");
         if (this.success == null) {
            sb.append("null");
         } else {
            sb.append(this.success);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      static {
         schemes.put(StandardScheme.class, new getStatusDetails_resultStandardSchemeFactory());
         schemes.put(TupleScheme.class, new getStatusDetails_resultTupleSchemeFactory());
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(FacebookService.getStatusDetails_result._Fields.SUCCESS, new FieldMetaData("success", (byte)3, new FieldValueMetaData((byte)11)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(getStatusDetails_result.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         SUCCESS((short)0, "success");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 0:
                  return SUCCESS;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class getStatusDetails_resultStandardSchemeFactory implements SchemeFactory {
         private getStatusDetails_resultStandardSchemeFactory() {
         }

         public getStatusDetails_resultStandardScheme getScheme() {
            return new getStatusDetails_resultStandardScheme();
         }
      }

      private static class getStatusDetails_resultStandardScheme extends StandardScheme {
         private getStatusDetails_resultStandardScheme() {
         }

         public void read(TProtocol iprot, getStatusDetails_result struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 0:
                     if (schemeField.type == 11) {
                        struct.success = iprot.readString();
                        struct.setSuccessIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, getStatusDetails_result struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(FacebookService.getStatusDetails_result.STRUCT_DESC);
            if (struct.success != null) {
               oprot.writeFieldBegin(FacebookService.getStatusDetails_result.SUCCESS_FIELD_DESC);
               oprot.writeString(struct.success);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class getStatusDetails_resultTupleSchemeFactory implements SchemeFactory {
         private getStatusDetails_resultTupleSchemeFactory() {
         }

         public getStatusDetails_resultTupleScheme getScheme() {
            return new getStatusDetails_resultTupleScheme();
         }
      }

      private static class getStatusDetails_resultTupleScheme extends TupleScheme {
         private getStatusDetails_resultTupleScheme() {
         }

         public void write(TProtocol prot, getStatusDetails_result struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetSuccess()) {
               oprot.writeString(struct.success);
            }

         }

         public void read(TProtocol prot, getStatusDetails_result struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.success = iprot.readString();
               struct.setSuccessIsSet(true);
            }

         }
      }
   }

   public static class getCounters_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("getCounters_args");
      private static final Map schemes = new HashMap();
      public static final Map metaDataMap;

      public getCounters_args() {
      }

      public getCounters_args(getCounters_args other) {
      }

      public getCounters_args deepCopy() {
         return new getCounters_args(this);
      }

      public void clear() {
      }

      public void setFieldValue(_Fields field, Object value) {
         int var10000 = null.$SwitchMap$com$facebook$fb303$FacebookService$getCounters_args$_Fields[field.ordinal()];
      }

      public Object getFieldValue(_Fields field) {
         int var10000 = null.$SwitchMap$com$facebook$fb303$FacebookService$getCounters_args$_Fields[field.ordinal()];
         throw new IllegalStateException();
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            int var10000 = null.$SwitchMap$com$facebook$fb303$FacebookService$getCounters_args$_Fields[field.ordinal()];
            throw new IllegalStateException();
         }
      }

      public boolean equals(Object that) {
         if (that == null) {
            return false;
         } else {
            return that instanceof getCounters_args ? this.equals((getCounters_args)that) : false;
         }
      }

      public boolean equals(getCounters_args that) {
         return that != null;
      }

      public int hashCode() {
         List<Object> list = new ArrayList();
         return list.hashCode();
      }

      public int compareTo(getCounters_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            return 0;
         }
      }

      public _Fields fieldForId(int fieldId) {
         return FacebookService.getCounters_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         ((SchemeFactory)schemes.get(iprot.getScheme())).getScheme().read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         ((SchemeFactory)schemes.get(oprot.getScheme())).getScheme().write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("getCounters_args(");
         boolean first = true;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      static {
         schemes.put(StandardScheme.class, new getCounters_argsStandardSchemeFactory());
         schemes.put(TupleScheme.class, new getCounters_argsTupleSchemeFactory());
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(getCounters_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class getCounters_argsStandardSchemeFactory implements SchemeFactory {
         private getCounters_argsStandardSchemeFactory() {
         }

         public getCounters_argsStandardScheme getScheme() {
            return new getCounters_argsStandardScheme();
         }
      }

      private static class getCounters_argsStandardScheme extends StandardScheme {
         private getCounters_argsStandardScheme() {
         }

         public void read(TProtocol iprot, getCounters_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
                     iprot.readFieldEnd();
               }
            }
         }

         public void write(TProtocol oprot, getCounters_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(FacebookService.getCounters_args.STRUCT_DESC);
            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class getCounters_argsTupleSchemeFactory implements SchemeFactory {
         private getCounters_argsTupleSchemeFactory() {
         }

         public getCounters_argsTupleScheme getScheme() {
            return new getCounters_argsTupleScheme();
         }
      }

      private static class getCounters_argsTupleScheme extends TupleScheme {
         private getCounters_argsTupleScheme() {
         }

         public void write(TProtocol prot, getCounters_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
         }

         public void read(TProtocol prot, getCounters_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
         }
      }
   }

   public static class getCounters_result implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("getCounters_result");
      private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte)13, (short)0);
      private static final Map schemes = new HashMap();
      public Map success;
      public static final Map metaDataMap;

      public getCounters_result() {
      }

      public getCounters_result(Map success) {
         this();
         this.success = success;
      }

      public getCounters_result(getCounters_result other) {
         if (other.isSetSuccess()) {
            Map<String, Long> __this__success = new HashMap(other.success);
            this.success = __this__success;
         }

      }

      public getCounters_result deepCopy() {
         return new getCounters_result(this);
      }

      public void clear() {
         this.success = null;
      }

      public int getSuccessSize() {
         return this.success == null ? 0 : this.success.size();
      }

      public void putToSuccess(String key, long val) {
         if (this.success == null) {
            this.success = new HashMap();
         }

         this.success.put(key, val);
      }

      public Map getSuccess() {
         return this.success;
      }

      public getCounters_result setSuccess(Map success) {
         this.success = success;
         return this;
      }

      public void unsetSuccess() {
         this.success = null;
      }

      public boolean isSetSuccess() {
         return this.success != null;
      }

      public void setSuccessIsSet(boolean value) {
         if (!value) {
            this.success = null;
         }

      }

      public void setFieldValue(_Fields field, Object value) {
         switch (field) {
            case SUCCESS:
               if (value == null) {
                  this.unsetSuccess();
               } else {
                  this.setSuccess((Map)value);
               }
            default:
         }
      }

      public Object getFieldValue(_Fields field) {
         switch (field) {
            case SUCCESS:
               return this.getSuccess();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case SUCCESS:
                  return this.isSetSuccess();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         if (that == null) {
            return false;
         } else {
            return that instanceof getCounters_result ? this.equals((getCounters_result)that) : false;
         }
      }

      public boolean equals(getCounters_result that) {
         if (that == null) {
            return false;
         } else {
            boolean this_present_success = this.isSetSuccess();
            boolean that_present_success = that.isSetSuccess();
            if (this_present_success || that_present_success) {
               if (!this_present_success || !that_present_success) {
                  return false;
               }

               if (!this.success.equals(that.success)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         List<Object> list = new ArrayList();
         boolean present_success = this.isSetSuccess();
         list.add(present_success);
         if (present_success) {
            list.add(this.success);
         }

         return list.hashCode();
      }

      public int compareTo(getCounters_result other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.valueOf(this.isSetSuccess()).compareTo(other.isSetSuccess());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSuccess()) {
                  lastComparison = TBaseHelper.compareTo(this.success, other.success);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      public _Fields fieldForId(int fieldId) {
         return FacebookService.getCounters_result._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         ((SchemeFactory)schemes.get(iprot.getScheme())).getScheme().read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         ((SchemeFactory)schemes.get(oprot.getScheme())).getScheme().write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("getCounters_result(");
         boolean first = true;
         sb.append("success:");
         if (this.success == null) {
            sb.append("null");
         } else {
            sb.append(this.success);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      static {
         schemes.put(StandardScheme.class, new getCounters_resultStandardSchemeFactory());
         schemes.put(TupleScheme.class, new getCounters_resultTupleSchemeFactory());
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(FacebookService.getCounters_result._Fields.SUCCESS, new FieldMetaData("success", (byte)3, new MapMetaData((byte)13, new FieldValueMetaData((byte)11), new FieldValueMetaData((byte)10))));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(getCounters_result.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         SUCCESS((short)0, "success");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 0:
                  return SUCCESS;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class getCounters_resultStandardSchemeFactory implements SchemeFactory {
         private getCounters_resultStandardSchemeFactory() {
         }

         public getCounters_resultStandardScheme getScheme() {
            return new getCounters_resultStandardScheme();
         }
      }

      private static class getCounters_resultStandardScheme extends StandardScheme {
         private getCounters_resultStandardScheme() {
         }

         public void read(TProtocol iprot, getCounters_result struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 0:
                     if (schemeField.type != 13) {
                        TProtocolUtil.skip(iprot, schemeField.type);
                        break;
                     }

                     TMap _map0 = iprot.readMapBegin();
                     struct.success = new HashMap(2 * _map0.size);

                     for(int _i3 = 0; _i3 < _map0.size; ++_i3) {
                        String _key1 = iprot.readString();
                        long _val2 = iprot.readI64();
                        struct.success.put(_key1, _val2);
                     }

                     iprot.readMapEnd();
                     struct.setSuccessIsSet(true);
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, getCounters_result struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(FacebookService.getCounters_result.STRUCT_DESC);
            if (struct.success != null) {
               oprot.writeFieldBegin(FacebookService.getCounters_result.SUCCESS_FIELD_DESC);
               oprot.writeMapBegin(new TMap((byte)11, (byte)10, struct.success.size()));

               for(Map.Entry _iter4 : struct.success.entrySet()) {
                  oprot.writeString((String)_iter4.getKey());
                  oprot.writeI64((Long)_iter4.getValue());
               }

               oprot.writeMapEnd();
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class getCounters_resultTupleSchemeFactory implements SchemeFactory {
         private getCounters_resultTupleSchemeFactory() {
         }

         public getCounters_resultTupleScheme getScheme() {
            return new getCounters_resultTupleScheme();
         }
      }

      private static class getCounters_resultTupleScheme extends TupleScheme {
         private getCounters_resultTupleScheme() {
         }

         public void write(TProtocol prot, getCounters_result struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetSuccess()) {
               oprot.writeI32(struct.success.size());

               for(Map.Entry _iter5 : struct.success.entrySet()) {
                  oprot.writeString((String)_iter5.getKey());
                  oprot.writeI64((Long)_iter5.getValue());
               }
            }

         }

         public void read(TProtocol prot, getCounters_result struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               TMap _map6 = new TMap((byte)11, (byte)10, iprot.readI32());
               struct.success = new HashMap(2 * _map6.size);

               for(int _i9 = 0; _i9 < _map6.size; ++_i9) {
                  String _key7 = iprot.readString();
                  long _val8 = iprot.readI64();
                  struct.success.put(_key7, _val8);
               }

               struct.setSuccessIsSet(true);
            }

         }
      }
   }

   public static class getCounter_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("getCounter_args");
      private static final TField KEY_FIELD_DESC = new TField("key", (byte)11, (short)1);
      private static final Map schemes = new HashMap();
      public String key;
      public static final Map metaDataMap;

      public getCounter_args() {
      }

      public getCounter_args(String key) {
         this();
         this.key = key;
      }

      public getCounter_args(getCounter_args other) {
         if (other.isSetKey()) {
            this.key = other.key;
         }

      }

      public getCounter_args deepCopy() {
         return new getCounter_args(this);
      }

      public void clear() {
         this.key = null;
      }

      public String getKey() {
         return this.key;
      }

      public getCounter_args setKey(String key) {
         this.key = key;
         return this;
      }

      public void unsetKey() {
         this.key = null;
      }

      public boolean isSetKey() {
         return this.key != null;
      }

      public void setKeyIsSet(boolean value) {
         if (!value) {
            this.key = null;
         }

      }

      public void setFieldValue(_Fields field, Object value) {
         switch (field) {
            case KEY:
               if (value == null) {
                  this.unsetKey();
               } else {
                  this.setKey((String)value);
               }
            default:
         }
      }

      public Object getFieldValue(_Fields field) {
         switch (field) {
            case KEY:
               return this.getKey();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case KEY:
                  return this.isSetKey();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         if (that == null) {
            return false;
         } else {
            return that instanceof getCounter_args ? this.equals((getCounter_args)that) : false;
         }
      }

      public boolean equals(getCounter_args that) {
         if (that == null) {
            return false;
         } else {
            boolean this_present_key = this.isSetKey();
            boolean that_present_key = that.isSetKey();
            if (this_present_key || that_present_key) {
               if (!this_present_key || !that_present_key) {
                  return false;
               }

               if (!this.key.equals(that.key)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         List<Object> list = new ArrayList();
         boolean present_key = this.isSetKey();
         list.add(present_key);
         if (present_key) {
            list.add(this.key);
         }

         return list.hashCode();
      }

      public int compareTo(getCounter_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.valueOf(this.isSetKey()).compareTo(other.isSetKey());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetKey()) {
                  lastComparison = TBaseHelper.compareTo(this.key, other.key);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      public _Fields fieldForId(int fieldId) {
         return FacebookService.getCounter_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         ((SchemeFactory)schemes.get(iprot.getScheme())).getScheme().read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         ((SchemeFactory)schemes.get(oprot.getScheme())).getScheme().write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("getCounter_args(");
         boolean first = true;
         sb.append("key:");
         if (this.key == null) {
            sb.append("null");
         } else {
            sb.append(this.key);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      static {
         schemes.put(StandardScheme.class, new getCounter_argsStandardSchemeFactory());
         schemes.put(TupleScheme.class, new getCounter_argsTupleSchemeFactory());
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(FacebookService.getCounter_args._Fields.KEY, new FieldMetaData("key", (byte)3, new FieldValueMetaData((byte)11)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(getCounter_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         KEY((short)1, "key");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 1:
                  return KEY;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class getCounter_argsStandardSchemeFactory implements SchemeFactory {
         private getCounter_argsStandardSchemeFactory() {
         }

         public getCounter_argsStandardScheme getScheme() {
            return new getCounter_argsStandardScheme();
         }
      }

      private static class getCounter_argsStandardScheme extends StandardScheme {
         private getCounter_argsStandardScheme() {
         }

         public void read(TProtocol iprot, getCounter_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 1:
                     if (schemeField.type == 11) {
                        struct.key = iprot.readString();
                        struct.setKeyIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, getCounter_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(FacebookService.getCounter_args.STRUCT_DESC);
            if (struct.key != null) {
               oprot.writeFieldBegin(FacebookService.getCounter_args.KEY_FIELD_DESC);
               oprot.writeString(struct.key);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class getCounter_argsTupleSchemeFactory implements SchemeFactory {
         private getCounter_argsTupleSchemeFactory() {
         }

         public getCounter_argsTupleScheme getScheme() {
            return new getCounter_argsTupleScheme();
         }
      }

      private static class getCounter_argsTupleScheme extends TupleScheme {
         private getCounter_argsTupleScheme() {
         }

         public void write(TProtocol prot, getCounter_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetKey()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetKey()) {
               oprot.writeString(struct.key);
            }

         }

         public void read(TProtocol prot, getCounter_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.key = iprot.readString();
               struct.setKeyIsSet(true);
            }

         }
      }
   }

   public static class getCounter_result implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("getCounter_result");
      private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte)10, (short)0);
      private static final Map schemes = new HashMap();
      public long success;
      private static final int __SUCCESS_ISSET_ID = 0;
      private byte __isset_bitfield;
      public static final Map metaDataMap;

      public getCounter_result() {
         this.__isset_bitfield = 0;
      }

      public getCounter_result(long success) {
         this();
         this.success = success;
         this.setSuccessIsSet(true);
      }

      public getCounter_result(getCounter_result other) {
         this.__isset_bitfield = 0;
         this.__isset_bitfield = other.__isset_bitfield;
         this.success = other.success;
      }

      public getCounter_result deepCopy() {
         return new getCounter_result(this);
      }

      public void clear() {
         this.setSuccessIsSet(false);
         this.success = 0L;
      }

      public long getSuccess() {
         return this.success;
      }

      public getCounter_result setSuccess(long success) {
         this.success = success;
         this.setSuccessIsSet(true);
         return this;
      }

      public void unsetSuccess() {
         this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
      }

      public boolean isSetSuccess() {
         return EncodingUtils.testBit(this.__isset_bitfield, 0);
      }

      public void setSuccessIsSet(boolean value) {
         this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
      }

      public void setFieldValue(_Fields field, Object value) {
         switch (field) {
            case SUCCESS:
               if (value == null) {
                  this.unsetSuccess();
               } else {
                  this.setSuccess((Long)value);
               }
            default:
         }
      }

      public Object getFieldValue(_Fields field) {
         switch (field) {
            case SUCCESS:
               return this.getSuccess();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case SUCCESS:
                  return this.isSetSuccess();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         if (that == null) {
            return false;
         } else {
            return that instanceof getCounter_result ? this.equals((getCounter_result)that) : false;
         }
      }

      public boolean equals(getCounter_result that) {
         if (that == null) {
            return false;
         } else {
            boolean this_present_success = true;
            boolean that_present_success = true;
            if (this_present_success || that_present_success) {
               if (!this_present_success || !that_present_success) {
                  return false;
               }

               if (this.success != that.success) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         List<Object> list = new ArrayList();
         boolean present_success = true;
         list.add(present_success);
         if (present_success) {
            list.add(this.success);
         }

         return list.hashCode();
      }

      public int compareTo(getCounter_result other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.valueOf(this.isSetSuccess()).compareTo(other.isSetSuccess());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSuccess()) {
                  lastComparison = TBaseHelper.compareTo(this.success, other.success);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      public _Fields fieldForId(int fieldId) {
         return FacebookService.getCounter_result._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         ((SchemeFactory)schemes.get(iprot.getScheme())).getScheme().read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         ((SchemeFactory)schemes.get(oprot.getScheme())).getScheme().write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("getCounter_result(");
         boolean first = true;
         sb.append("success:");
         sb.append(this.success);
         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.__isset_bitfield = 0;
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      static {
         schemes.put(StandardScheme.class, new getCounter_resultStandardSchemeFactory());
         schemes.put(TupleScheme.class, new getCounter_resultTupleSchemeFactory());
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(FacebookService.getCounter_result._Fields.SUCCESS, new FieldMetaData("success", (byte)3, new FieldValueMetaData((byte)10)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(getCounter_result.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         SUCCESS((short)0, "success");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 0:
                  return SUCCESS;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class getCounter_resultStandardSchemeFactory implements SchemeFactory {
         private getCounter_resultStandardSchemeFactory() {
         }

         public getCounter_resultStandardScheme getScheme() {
            return new getCounter_resultStandardScheme();
         }
      }

      private static class getCounter_resultStandardScheme extends StandardScheme {
         private getCounter_resultStandardScheme() {
         }

         public void read(TProtocol iprot, getCounter_result struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 0:
                     if (schemeField.type == 10) {
                        struct.success = iprot.readI64();
                        struct.setSuccessIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, getCounter_result struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(FacebookService.getCounter_result.STRUCT_DESC);
            if (struct.isSetSuccess()) {
               oprot.writeFieldBegin(FacebookService.getCounter_result.SUCCESS_FIELD_DESC);
               oprot.writeI64(struct.success);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class getCounter_resultTupleSchemeFactory implements SchemeFactory {
         private getCounter_resultTupleSchemeFactory() {
         }

         public getCounter_resultTupleScheme getScheme() {
            return new getCounter_resultTupleScheme();
         }
      }

      private static class getCounter_resultTupleScheme extends TupleScheme {
         private getCounter_resultTupleScheme() {
         }

         public void write(TProtocol prot, getCounter_result struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetSuccess()) {
               oprot.writeI64(struct.success);
            }

         }

         public void read(TProtocol prot, getCounter_result struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.success = iprot.readI64();
               struct.setSuccessIsSet(true);
            }

         }
      }
   }

   public static class setOption_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("setOption_args");
      private static final TField KEY_FIELD_DESC = new TField("key", (byte)11, (short)1);
      private static final TField VALUE_FIELD_DESC = new TField("value", (byte)11, (short)2);
      private static final Map schemes = new HashMap();
      public String key;
      public String value;
      public static final Map metaDataMap;

      public setOption_args() {
      }

      public setOption_args(String key, String value) {
         this();
         this.key = key;
         this.value = value;
      }

      public setOption_args(setOption_args other) {
         if (other.isSetKey()) {
            this.key = other.key;
         }

         if (other.isSetValue()) {
            this.value = other.value;
         }

      }

      public setOption_args deepCopy() {
         return new setOption_args(this);
      }

      public void clear() {
         this.key = null;
         this.value = null;
      }

      public String getKey() {
         return this.key;
      }

      public setOption_args setKey(String key) {
         this.key = key;
         return this;
      }

      public void unsetKey() {
         this.key = null;
      }

      public boolean isSetKey() {
         return this.key != null;
      }

      public void setKeyIsSet(boolean value) {
         if (!value) {
            this.key = null;
         }

      }

      public String getValue() {
         return this.value;
      }

      public setOption_args setValue(String value) {
         this.value = value;
         return this;
      }

      public void unsetValue() {
         this.value = null;
      }

      public boolean isSetValue() {
         return this.value != null;
      }

      public void setValueIsSet(boolean value) {
         if (!value) {
            this.value = null;
         }

      }

      public void setFieldValue(_Fields field, Object value) {
         switch (field) {
            case KEY:
               if (value == null) {
                  this.unsetKey();
               } else {
                  this.setKey((String)value);
               }
               break;
            case VALUE:
               if (value == null) {
                  this.unsetValue();
               } else {
                  this.setValue((String)value);
               }
         }

      }

      public Object getFieldValue(_Fields field) {
         switch (field) {
            case KEY:
               return this.getKey();
            case VALUE:
               return this.getValue();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case KEY:
                  return this.isSetKey();
               case VALUE:
                  return this.isSetValue();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         if (that == null) {
            return false;
         } else {
            return that instanceof setOption_args ? this.equals((setOption_args)that) : false;
         }
      }

      public boolean equals(setOption_args that) {
         if (that == null) {
            return false;
         } else {
            boolean this_present_key = this.isSetKey();
            boolean that_present_key = that.isSetKey();
            if (this_present_key || that_present_key) {
               if (!this_present_key || !that_present_key) {
                  return false;
               }

               if (!this.key.equals(that.key)) {
                  return false;
               }
            }

            boolean this_present_value = this.isSetValue();
            boolean that_present_value = that.isSetValue();
            if (this_present_value || that_present_value) {
               if (!this_present_value || !that_present_value) {
                  return false;
               }

               if (!this.value.equals(that.value)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         List<Object> list = new ArrayList();
         boolean present_key = this.isSetKey();
         list.add(present_key);
         if (present_key) {
            list.add(this.key);
         }

         boolean present_value = this.isSetValue();
         list.add(present_value);
         if (present_value) {
            list.add(this.value);
         }

         return list.hashCode();
      }

      public int compareTo(setOption_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.valueOf(this.isSetKey()).compareTo(other.isSetKey());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetKey()) {
                  lastComparison = TBaseHelper.compareTo(this.key, other.key);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.valueOf(this.isSetValue()).compareTo(other.isSetValue());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetValue()) {
                     lastComparison = TBaseHelper.compareTo(this.value, other.value);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  return 0;
               }
            }
         }
      }

      public _Fields fieldForId(int fieldId) {
         return FacebookService.setOption_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         ((SchemeFactory)schemes.get(iprot.getScheme())).getScheme().read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         ((SchemeFactory)schemes.get(oprot.getScheme())).getScheme().write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("setOption_args(");
         boolean first = true;
         sb.append("key:");
         if (this.key == null) {
            sb.append("null");
         } else {
            sb.append(this.key);
         }

         first = false;
         if (!first) {
            sb.append(", ");
         }

         sb.append("value:");
         if (this.value == null) {
            sb.append("null");
         } else {
            sb.append(this.value);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      static {
         schemes.put(StandardScheme.class, new setOption_argsStandardSchemeFactory());
         schemes.put(TupleScheme.class, new setOption_argsTupleSchemeFactory());
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(FacebookService.setOption_args._Fields.KEY, new FieldMetaData("key", (byte)3, new FieldValueMetaData((byte)11)));
         tmpMap.put(FacebookService.setOption_args._Fields.VALUE, new FieldMetaData("value", (byte)3, new FieldValueMetaData((byte)11)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(setOption_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         KEY((short)1, "key"),
         VALUE((short)2, "value");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 1:
                  return KEY;
               case 2:
                  return VALUE;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class setOption_argsStandardSchemeFactory implements SchemeFactory {
         private setOption_argsStandardSchemeFactory() {
         }

         public setOption_argsStandardScheme getScheme() {
            return new setOption_argsStandardScheme();
         }
      }

      private static class setOption_argsStandardScheme extends StandardScheme {
         private setOption_argsStandardScheme() {
         }

         public void read(TProtocol iprot, setOption_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 1:
                     if (schemeField.type == 11) {
                        struct.key = iprot.readString();
                        struct.setKeyIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  case 2:
                     if (schemeField.type == 11) {
                        struct.value = iprot.readString();
                        struct.setValueIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, setOption_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(FacebookService.setOption_args.STRUCT_DESC);
            if (struct.key != null) {
               oprot.writeFieldBegin(FacebookService.setOption_args.KEY_FIELD_DESC);
               oprot.writeString(struct.key);
               oprot.writeFieldEnd();
            }

            if (struct.value != null) {
               oprot.writeFieldBegin(FacebookService.setOption_args.VALUE_FIELD_DESC);
               oprot.writeString(struct.value);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class setOption_argsTupleSchemeFactory implements SchemeFactory {
         private setOption_argsTupleSchemeFactory() {
         }

         public setOption_argsTupleScheme getScheme() {
            return new setOption_argsTupleScheme();
         }
      }

      private static class setOption_argsTupleScheme extends TupleScheme {
         private setOption_argsTupleScheme() {
         }

         public void write(TProtocol prot, setOption_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetKey()) {
               optionals.set(0);
            }

            if (struct.isSetValue()) {
               optionals.set(1);
            }

            oprot.writeBitSet(optionals, 2);
            if (struct.isSetKey()) {
               oprot.writeString(struct.key);
            }

            if (struct.isSetValue()) {
               oprot.writeString(struct.value);
            }

         }

         public void read(TProtocol prot, setOption_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(2);
            if (incoming.get(0)) {
               struct.key = iprot.readString();
               struct.setKeyIsSet(true);
            }

            if (incoming.get(1)) {
               struct.value = iprot.readString();
               struct.setValueIsSet(true);
            }

         }
      }
   }

   public static class setOption_result implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("setOption_result");
      private static final Map schemes = new HashMap();
      public static final Map metaDataMap;

      public setOption_result() {
      }

      public setOption_result(setOption_result other) {
      }

      public setOption_result deepCopy() {
         return new setOption_result(this);
      }

      public void clear() {
      }

      public void setFieldValue(_Fields field, Object value) {
         int var10000 = null.$SwitchMap$com$facebook$fb303$FacebookService$setOption_result$_Fields[field.ordinal()];
      }

      public Object getFieldValue(_Fields field) {
         int var10000 = null.$SwitchMap$com$facebook$fb303$FacebookService$setOption_result$_Fields[field.ordinal()];
         throw new IllegalStateException();
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            int var10000 = null.$SwitchMap$com$facebook$fb303$FacebookService$setOption_result$_Fields[field.ordinal()];
            throw new IllegalStateException();
         }
      }

      public boolean equals(Object that) {
         if (that == null) {
            return false;
         } else {
            return that instanceof setOption_result ? this.equals((setOption_result)that) : false;
         }
      }

      public boolean equals(setOption_result that) {
         return that != null;
      }

      public int hashCode() {
         List<Object> list = new ArrayList();
         return list.hashCode();
      }

      public int compareTo(setOption_result other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            return 0;
         }
      }

      public _Fields fieldForId(int fieldId) {
         return FacebookService.setOption_result._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         ((SchemeFactory)schemes.get(iprot.getScheme())).getScheme().read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         ((SchemeFactory)schemes.get(oprot.getScheme())).getScheme().write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("setOption_result(");
         boolean first = true;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      static {
         schemes.put(StandardScheme.class, new setOption_resultStandardSchemeFactory());
         schemes.put(TupleScheme.class, new setOption_resultTupleSchemeFactory());
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(setOption_result.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class setOption_resultStandardSchemeFactory implements SchemeFactory {
         private setOption_resultStandardSchemeFactory() {
         }

         public setOption_resultStandardScheme getScheme() {
            return new setOption_resultStandardScheme();
         }
      }

      private static class setOption_resultStandardScheme extends StandardScheme {
         private setOption_resultStandardScheme() {
         }

         public void read(TProtocol iprot, setOption_result struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
                     iprot.readFieldEnd();
               }
            }
         }

         public void write(TProtocol oprot, setOption_result struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(FacebookService.setOption_result.STRUCT_DESC);
            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class setOption_resultTupleSchemeFactory implements SchemeFactory {
         private setOption_resultTupleSchemeFactory() {
         }

         public setOption_resultTupleScheme getScheme() {
            return new setOption_resultTupleScheme();
         }
      }

      private static class setOption_resultTupleScheme extends TupleScheme {
         private setOption_resultTupleScheme() {
         }

         public void write(TProtocol prot, setOption_result struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
         }

         public void read(TProtocol prot, setOption_result struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
         }
      }
   }

   public static class getOption_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("getOption_args");
      private static final TField KEY_FIELD_DESC = new TField("key", (byte)11, (short)1);
      private static final Map schemes = new HashMap();
      public String key;
      public static final Map metaDataMap;

      public getOption_args() {
      }

      public getOption_args(String key) {
         this();
         this.key = key;
      }

      public getOption_args(getOption_args other) {
         if (other.isSetKey()) {
            this.key = other.key;
         }

      }

      public getOption_args deepCopy() {
         return new getOption_args(this);
      }

      public void clear() {
         this.key = null;
      }

      public String getKey() {
         return this.key;
      }

      public getOption_args setKey(String key) {
         this.key = key;
         return this;
      }

      public void unsetKey() {
         this.key = null;
      }

      public boolean isSetKey() {
         return this.key != null;
      }

      public void setKeyIsSet(boolean value) {
         if (!value) {
            this.key = null;
         }

      }

      public void setFieldValue(_Fields field, Object value) {
         switch (field) {
            case KEY:
               if (value == null) {
                  this.unsetKey();
               } else {
                  this.setKey((String)value);
               }
            default:
         }
      }

      public Object getFieldValue(_Fields field) {
         switch (field) {
            case KEY:
               return this.getKey();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case KEY:
                  return this.isSetKey();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         if (that == null) {
            return false;
         } else {
            return that instanceof getOption_args ? this.equals((getOption_args)that) : false;
         }
      }

      public boolean equals(getOption_args that) {
         if (that == null) {
            return false;
         } else {
            boolean this_present_key = this.isSetKey();
            boolean that_present_key = that.isSetKey();
            if (this_present_key || that_present_key) {
               if (!this_present_key || !that_present_key) {
                  return false;
               }

               if (!this.key.equals(that.key)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         List<Object> list = new ArrayList();
         boolean present_key = this.isSetKey();
         list.add(present_key);
         if (present_key) {
            list.add(this.key);
         }

         return list.hashCode();
      }

      public int compareTo(getOption_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.valueOf(this.isSetKey()).compareTo(other.isSetKey());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetKey()) {
                  lastComparison = TBaseHelper.compareTo(this.key, other.key);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      public _Fields fieldForId(int fieldId) {
         return FacebookService.getOption_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         ((SchemeFactory)schemes.get(iprot.getScheme())).getScheme().read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         ((SchemeFactory)schemes.get(oprot.getScheme())).getScheme().write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("getOption_args(");
         boolean first = true;
         sb.append("key:");
         if (this.key == null) {
            sb.append("null");
         } else {
            sb.append(this.key);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      static {
         schemes.put(StandardScheme.class, new getOption_argsStandardSchemeFactory());
         schemes.put(TupleScheme.class, new getOption_argsTupleSchemeFactory());
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(FacebookService.getOption_args._Fields.KEY, new FieldMetaData("key", (byte)3, new FieldValueMetaData((byte)11)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(getOption_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         KEY((short)1, "key");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 1:
                  return KEY;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class getOption_argsStandardSchemeFactory implements SchemeFactory {
         private getOption_argsStandardSchemeFactory() {
         }

         public getOption_argsStandardScheme getScheme() {
            return new getOption_argsStandardScheme();
         }
      }

      private static class getOption_argsStandardScheme extends StandardScheme {
         private getOption_argsStandardScheme() {
         }

         public void read(TProtocol iprot, getOption_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 1:
                     if (schemeField.type == 11) {
                        struct.key = iprot.readString();
                        struct.setKeyIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, getOption_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(FacebookService.getOption_args.STRUCT_DESC);
            if (struct.key != null) {
               oprot.writeFieldBegin(FacebookService.getOption_args.KEY_FIELD_DESC);
               oprot.writeString(struct.key);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class getOption_argsTupleSchemeFactory implements SchemeFactory {
         private getOption_argsTupleSchemeFactory() {
         }

         public getOption_argsTupleScheme getScheme() {
            return new getOption_argsTupleScheme();
         }
      }

      private static class getOption_argsTupleScheme extends TupleScheme {
         private getOption_argsTupleScheme() {
         }

         public void write(TProtocol prot, getOption_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetKey()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetKey()) {
               oprot.writeString(struct.key);
            }

         }

         public void read(TProtocol prot, getOption_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.key = iprot.readString();
               struct.setKeyIsSet(true);
            }

         }
      }
   }

   public static class getOption_result implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("getOption_result");
      private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte)11, (short)0);
      private static final Map schemes = new HashMap();
      public String success;
      public static final Map metaDataMap;

      public getOption_result() {
      }

      public getOption_result(String success) {
         this();
         this.success = success;
      }

      public getOption_result(getOption_result other) {
         if (other.isSetSuccess()) {
            this.success = other.success;
         }

      }

      public getOption_result deepCopy() {
         return new getOption_result(this);
      }

      public void clear() {
         this.success = null;
      }

      public String getSuccess() {
         return this.success;
      }

      public getOption_result setSuccess(String success) {
         this.success = success;
         return this;
      }

      public void unsetSuccess() {
         this.success = null;
      }

      public boolean isSetSuccess() {
         return this.success != null;
      }

      public void setSuccessIsSet(boolean value) {
         if (!value) {
            this.success = null;
         }

      }

      public void setFieldValue(_Fields field, Object value) {
         switch (field) {
            case SUCCESS:
               if (value == null) {
                  this.unsetSuccess();
               } else {
                  this.setSuccess((String)value);
               }
            default:
         }
      }

      public Object getFieldValue(_Fields field) {
         switch (field) {
            case SUCCESS:
               return this.getSuccess();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case SUCCESS:
                  return this.isSetSuccess();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         if (that == null) {
            return false;
         } else {
            return that instanceof getOption_result ? this.equals((getOption_result)that) : false;
         }
      }

      public boolean equals(getOption_result that) {
         if (that == null) {
            return false;
         } else {
            boolean this_present_success = this.isSetSuccess();
            boolean that_present_success = that.isSetSuccess();
            if (this_present_success || that_present_success) {
               if (!this_present_success || !that_present_success) {
                  return false;
               }

               if (!this.success.equals(that.success)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         List<Object> list = new ArrayList();
         boolean present_success = this.isSetSuccess();
         list.add(present_success);
         if (present_success) {
            list.add(this.success);
         }

         return list.hashCode();
      }

      public int compareTo(getOption_result other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.valueOf(this.isSetSuccess()).compareTo(other.isSetSuccess());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSuccess()) {
                  lastComparison = TBaseHelper.compareTo(this.success, other.success);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      public _Fields fieldForId(int fieldId) {
         return FacebookService.getOption_result._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         ((SchemeFactory)schemes.get(iprot.getScheme())).getScheme().read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         ((SchemeFactory)schemes.get(oprot.getScheme())).getScheme().write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("getOption_result(");
         boolean first = true;
         sb.append("success:");
         if (this.success == null) {
            sb.append("null");
         } else {
            sb.append(this.success);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      static {
         schemes.put(StandardScheme.class, new getOption_resultStandardSchemeFactory());
         schemes.put(TupleScheme.class, new getOption_resultTupleSchemeFactory());
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(FacebookService.getOption_result._Fields.SUCCESS, new FieldMetaData("success", (byte)3, new FieldValueMetaData((byte)11)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(getOption_result.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         SUCCESS((short)0, "success");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 0:
                  return SUCCESS;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class getOption_resultStandardSchemeFactory implements SchemeFactory {
         private getOption_resultStandardSchemeFactory() {
         }

         public getOption_resultStandardScheme getScheme() {
            return new getOption_resultStandardScheme();
         }
      }

      private static class getOption_resultStandardScheme extends StandardScheme {
         private getOption_resultStandardScheme() {
         }

         public void read(TProtocol iprot, getOption_result struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 0:
                     if (schemeField.type == 11) {
                        struct.success = iprot.readString();
                        struct.setSuccessIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, getOption_result struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(FacebookService.getOption_result.STRUCT_DESC);
            if (struct.success != null) {
               oprot.writeFieldBegin(FacebookService.getOption_result.SUCCESS_FIELD_DESC);
               oprot.writeString(struct.success);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class getOption_resultTupleSchemeFactory implements SchemeFactory {
         private getOption_resultTupleSchemeFactory() {
         }

         public getOption_resultTupleScheme getScheme() {
            return new getOption_resultTupleScheme();
         }
      }

      private static class getOption_resultTupleScheme extends TupleScheme {
         private getOption_resultTupleScheme() {
         }

         public void write(TProtocol prot, getOption_result struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetSuccess()) {
               oprot.writeString(struct.success);
            }

         }

         public void read(TProtocol prot, getOption_result struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.success = iprot.readString();
               struct.setSuccessIsSet(true);
            }

         }
      }
   }

   public static class getOptions_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("getOptions_args");
      private static final Map schemes = new HashMap();
      public static final Map metaDataMap;

      public getOptions_args() {
      }

      public getOptions_args(getOptions_args other) {
      }

      public getOptions_args deepCopy() {
         return new getOptions_args(this);
      }

      public void clear() {
      }

      public void setFieldValue(_Fields field, Object value) {
         int var10000 = null.$SwitchMap$com$facebook$fb303$FacebookService$getOptions_args$_Fields[field.ordinal()];
      }

      public Object getFieldValue(_Fields field) {
         int var10000 = null.$SwitchMap$com$facebook$fb303$FacebookService$getOptions_args$_Fields[field.ordinal()];
         throw new IllegalStateException();
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            int var10000 = null.$SwitchMap$com$facebook$fb303$FacebookService$getOptions_args$_Fields[field.ordinal()];
            throw new IllegalStateException();
         }
      }

      public boolean equals(Object that) {
         if (that == null) {
            return false;
         } else {
            return that instanceof getOptions_args ? this.equals((getOptions_args)that) : false;
         }
      }

      public boolean equals(getOptions_args that) {
         return that != null;
      }

      public int hashCode() {
         List<Object> list = new ArrayList();
         return list.hashCode();
      }

      public int compareTo(getOptions_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            return 0;
         }
      }

      public _Fields fieldForId(int fieldId) {
         return FacebookService.getOptions_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         ((SchemeFactory)schemes.get(iprot.getScheme())).getScheme().read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         ((SchemeFactory)schemes.get(oprot.getScheme())).getScheme().write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("getOptions_args(");
         boolean first = true;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      static {
         schemes.put(StandardScheme.class, new getOptions_argsStandardSchemeFactory());
         schemes.put(TupleScheme.class, new getOptions_argsTupleSchemeFactory());
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(getOptions_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class getOptions_argsStandardSchemeFactory implements SchemeFactory {
         private getOptions_argsStandardSchemeFactory() {
         }

         public getOptions_argsStandardScheme getScheme() {
            return new getOptions_argsStandardScheme();
         }
      }

      private static class getOptions_argsStandardScheme extends StandardScheme {
         private getOptions_argsStandardScheme() {
         }

         public void read(TProtocol iprot, getOptions_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
                     iprot.readFieldEnd();
               }
            }
         }

         public void write(TProtocol oprot, getOptions_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(FacebookService.getOptions_args.STRUCT_DESC);
            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class getOptions_argsTupleSchemeFactory implements SchemeFactory {
         private getOptions_argsTupleSchemeFactory() {
         }

         public getOptions_argsTupleScheme getScheme() {
            return new getOptions_argsTupleScheme();
         }
      }

      private static class getOptions_argsTupleScheme extends TupleScheme {
         private getOptions_argsTupleScheme() {
         }

         public void write(TProtocol prot, getOptions_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
         }

         public void read(TProtocol prot, getOptions_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
         }
      }
   }

   public static class getOptions_result implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("getOptions_result");
      private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte)13, (short)0);
      private static final Map schemes = new HashMap();
      public Map success;
      public static final Map metaDataMap;

      public getOptions_result() {
      }

      public getOptions_result(Map success) {
         this();
         this.success = success;
      }

      public getOptions_result(getOptions_result other) {
         if (other.isSetSuccess()) {
            Map<String, String> __this__success = new HashMap(other.success);
            this.success = __this__success;
         }

      }

      public getOptions_result deepCopy() {
         return new getOptions_result(this);
      }

      public void clear() {
         this.success = null;
      }

      public int getSuccessSize() {
         return this.success == null ? 0 : this.success.size();
      }

      public void putToSuccess(String key, String val) {
         if (this.success == null) {
            this.success = new HashMap();
         }

         this.success.put(key, val);
      }

      public Map getSuccess() {
         return this.success;
      }

      public getOptions_result setSuccess(Map success) {
         this.success = success;
         return this;
      }

      public void unsetSuccess() {
         this.success = null;
      }

      public boolean isSetSuccess() {
         return this.success != null;
      }

      public void setSuccessIsSet(boolean value) {
         if (!value) {
            this.success = null;
         }

      }

      public void setFieldValue(_Fields field, Object value) {
         switch (field) {
            case SUCCESS:
               if (value == null) {
                  this.unsetSuccess();
               } else {
                  this.setSuccess((Map)value);
               }
            default:
         }
      }

      public Object getFieldValue(_Fields field) {
         switch (field) {
            case SUCCESS:
               return this.getSuccess();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case SUCCESS:
                  return this.isSetSuccess();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         if (that == null) {
            return false;
         } else {
            return that instanceof getOptions_result ? this.equals((getOptions_result)that) : false;
         }
      }

      public boolean equals(getOptions_result that) {
         if (that == null) {
            return false;
         } else {
            boolean this_present_success = this.isSetSuccess();
            boolean that_present_success = that.isSetSuccess();
            if (this_present_success || that_present_success) {
               if (!this_present_success || !that_present_success) {
                  return false;
               }

               if (!this.success.equals(that.success)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         List<Object> list = new ArrayList();
         boolean present_success = this.isSetSuccess();
         list.add(present_success);
         if (present_success) {
            list.add(this.success);
         }

         return list.hashCode();
      }

      public int compareTo(getOptions_result other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.valueOf(this.isSetSuccess()).compareTo(other.isSetSuccess());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSuccess()) {
                  lastComparison = TBaseHelper.compareTo(this.success, other.success);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      public _Fields fieldForId(int fieldId) {
         return FacebookService.getOptions_result._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         ((SchemeFactory)schemes.get(iprot.getScheme())).getScheme().read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         ((SchemeFactory)schemes.get(oprot.getScheme())).getScheme().write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("getOptions_result(");
         boolean first = true;
         sb.append("success:");
         if (this.success == null) {
            sb.append("null");
         } else {
            sb.append(this.success);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      static {
         schemes.put(StandardScheme.class, new getOptions_resultStandardSchemeFactory());
         schemes.put(TupleScheme.class, new getOptions_resultTupleSchemeFactory());
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(FacebookService.getOptions_result._Fields.SUCCESS, new FieldMetaData("success", (byte)3, new MapMetaData((byte)13, new FieldValueMetaData((byte)11), new FieldValueMetaData((byte)11))));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(getOptions_result.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         SUCCESS((short)0, "success");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 0:
                  return SUCCESS;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class getOptions_resultStandardSchemeFactory implements SchemeFactory {
         private getOptions_resultStandardSchemeFactory() {
         }

         public getOptions_resultStandardScheme getScheme() {
            return new getOptions_resultStandardScheme();
         }
      }

      private static class getOptions_resultStandardScheme extends StandardScheme {
         private getOptions_resultStandardScheme() {
         }

         public void read(TProtocol iprot, getOptions_result struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 0:
                     if (schemeField.type != 13) {
                        TProtocolUtil.skip(iprot, schemeField.type);
                        break;
                     }

                     TMap _map10 = iprot.readMapBegin();
                     struct.success = new HashMap(2 * _map10.size);

                     for(int _i13 = 0; _i13 < _map10.size; ++_i13) {
                        String _key11 = iprot.readString();
                        String _val12 = iprot.readString();
                        struct.success.put(_key11, _val12);
                     }

                     iprot.readMapEnd();
                     struct.setSuccessIsSet(true);
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, getOptions_result struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(FacebookService.getOptions_result.STRUCT_DESC);
            if (struct.success != null) {
               oprot.writeFieldBegin(FacebookService.getOptions_result.SUCCESS_FIELD_DESC);
               oprot.writeMapBegin(new TMap((byte)11, (byte)11, struct.success.size()));

               for(Map.Entry _iter14 : struct.success.entrySet()) {
                  oprot.writeString((String)_iter14.getKey());
                  oprot.writeString((String)_iter14.getValue());
               }

               oprot.writeMapEnd();
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class getOptions_resultTupleSchemeFactory implements SchemeFactory {
         private getOptions_resultTupleSchemeFactory() {
         }

         public getOptions_resultTupleScheme getScheme() {
            return new getOptions_resultTupleScheme();
         }
      }

      private static class getOptions_resultTupleScheme extends TupleScheme {
         private getOptions_resultTupleScheme() {
         }

         public void write(TProtocol prot, getOptions_result struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetSuccess()) {
               oprot.writeI32(struct.success.size());

               for(Map.Entry _iter15 : struct.success.entrySet()) {
                  oprot.writeString((String)_iter15.getKey());
                  oprot.writeString((String)_iter15.getValue());
               }
            }

         }

         public void read(TProtocol prot, getOptions_result struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               TMap _map16 = new TMap((byte)11, (byte)11, iprot.readI32());
               struct.success = new HashMap(2 * _map16.size);

               for(int _i19 = 0; _i19 < _map16.size; ++_i19) {
                  String _key17 = iprot.readString();
                  String _val18 = iprot.readString();
                  struct.success.put(_key17, _val18);
               }

               struct.setSuccessIsSet(true);
            }

         }
      }
   }

   public static class getCpuProfile_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("getCpuProfile_args");
      private static final TField PROFILE_DURATION_IN_SEC_FIELD_DESC = new TField("profileDurationInSec", (byte)8, (short)1);
      private static final Map schemes = new HashMap();
      public int profileDurationInSec;
      private static final int __PROFILEDURATIONINSEC_ISSET_ID = 0;
      private byte __isset_bitfield;
      public static final Map metaDataMap;

      public getCpuProfile_args() {
         this.__isset_bitfield = 0;
      }

      public getCpuProfile_args(int profileDurationInSec) {
         this();
         this.profileDurationInSec = profileDurationInSec;
         this.setProfileDurationInSecIsSet(true);
      }

      public getCpuProfile_args(getCpuProfile_args other) {
         this.__isset_bitfield = 0;
         this.__isset_bitfield = other.__isset_bitfield;
         this.profileDurationInSec = other.profileDurationInSec;
      }

      public getCpuProfile_args deepCopy() {
         return new getCpuProfile_args(this);
      }

      public void clear() {
         this.setProfileDurationInSecIsSet(false);
         this.profileDurationInSec = 0;
      }

      public int getProfileDurationInSec() {
         return this.profileDurationInSec;
      }

      public getCpuProfile_args setProfileDurationInSec(int profileDurationInSec) {
         this.profileDurationInSec = profileDurationInSec;
         this.setProfileDurationInSecIsSet(true);
         return this;
      }

      public void unsetProfileDurationInSec() {
         this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
      }

      public boolean isSetProfileDurationInSec() {
         return EncodingUtils.testBit(this.__isset_bitfield, 0);
      }

      public void setProfileDurationInSecIsSet(boolean value) {
         this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
      }

      public void setFieldValue(_Fields field, Object value) {
         switch (field) {
            case PROFILE_DURATION_IN_SEC:
               if (value == null) {
                  this.unsetProfileDurationInSec();
               } else {
                  this.setProfileDurationInSec((Integer)value);
               }
            default:
         }
      }

      public Object getFieldValue(_Fields field) {
         switch (field) {
            case PROFILE_DURATION_IN_SEC:
               return this.getProfileDurationInSec();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case PROFILE_DURATION_IN_SEC:
                  return this.isSetProfileDurationInSec();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         if (that == null) {
            return false;
         } else {
            return that instanceof getCpuProfile_args ? this.equals((getCpuProfile_args)that) : false;
         }
      }

      public boolean equals(getCpuProfile_args that) {
         if (that == null) {
            return false;
         } else {
            boolean this_present_profileDurationInSec = true;
            boolean that_present_profileDurationInSec = true;
            if (this_present_profileDurationInSec || that_present_profileDurationInSec) {
               if (!this_present_profileDurationInSec || !that_present_profileDurationInSec) {
                  return false;
               }

               if (this.profileDurationInSec != that.profileDurationInSec) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         List<Object> list = new ArrayList();
         boolean present_profileDurationInSec = true;
         list.add(present_profileDurationInSec);
         if (present_profileDurationInSec) {
            list.add(this.profileDurationInSec);
         }

         return list.hashCode();
      }

      public int compareTo(getCpuProfile_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.valueOf(this.isSetProfileDurationInSec()).compareTo(other.isSetProfileDurationInSec());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetProfileDurationInSec()) {
                  lastComparison = TBaseHelper.compareTo(this.profileDurationInSec, other.profileDurationInSec);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      public _Fields fieldForId(int fieldId) {
         return FacebookService.getCpuProfile_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         ((SchemeFactory)schemes.get(iprot.getScheme())).getScheme().read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         ((SchemeFactory)schemes.get(oprot.getScheme())).getScheme().write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("getCpuProfile_args(");
         boolean first = true;
         sb.append("profileDurationInSec:");
         sb.append(this.profileDurationInSec);
         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.__isset_bitfield = 0;
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      static {
         schemes.put(StandardScheme.class, new getCpuProfile_argsStandardSchemeFactory());
         schemes.put(TupleScheme.class, new getCpuProfile_argsTupleSchemeFactory());
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(FacebookService.getCpuProfile_args._Fields.PROFILE_DURATION_IN_SEC, new FieldMetaData("profileDurationInSec", (byte)3, new FieldValueMetaData((byte)8)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(getCpuProfile_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         PROFILE_DURATION_IN_SEC((short)1, "profileDurationInSec");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 1:
                  return PROFILE_DURATION_IN_SEC;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class getCpuProfile_argsStandardSchemeFactory implements SchemeFactory {
         private getCpuProfile_argsStandardSchemeFactory() {
         }

         public getCpuProfile_argsStandardScheme getScheme() {
            return new getCpuProfile_argsStandardScheme();
         }
      }

      private static class getCpuProfile_argsStandardScheme extends StandardScheme {
         private getCpuProfile_argsStandardScheme() {
         }

         public void read(TProtocol iprot, getCpuProfile_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 1:
                     if (schemeField.type == 8) {
                        struct.profileDurationInSec = iprot.readI32();
                        struct.setProfileDurationInSecIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, getCpuProfile_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(FacebookService.getCpuProfile_args.STRUCT_DESC);
            oprot.writeFieldBegin(FacebookService.getCpuProfile_args.PROFILE_DURATION_IN_SEC_FIELD_DESC);
            oprot.writeI32(struct.profileDurationInSec);
            oprot.writeFieldEnd();
            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class getCpuProfile_argsTupleSchemeFactory implements SchemeFactory {
         private getCpuProfile_argsTupleSchemeFactory() {
         }

         public getCpuProfile_argsTupleScheme getScheme() {
            return new getCpuProfile_argsTupleScheme();
         }
      }

      private static class getCpuProfile_argsTupleScheme extends TupleScheme {
         private getCpuProfile_argsTupleScheme() {
         }

         public void write(TProtocol prot, getCpuProfile_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetProfileDurationInSec()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetProfileDurationInSec()) {
               oprot.writeI32(struct.profileDurationInSec);
            }

         }

         public void read(TProtocol prot, getCpuProfile_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.profileDurationInSec = iprot.readI32();
               struct.setProfileDurationInSecIsSet(true);
            }

         }
      }
   }

   public static class getCpuProfile_result implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("getCpuProfile_result");
      private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte)11, (short)0);
      private static final Map schemes = new HashMap();
      public String success;
      public static final Map metaDataMap;

      public getCpuProfile_result() {
      }

      public getCpuProfile_result(String success) {
         this();
         this.success = success;
      }

      public getCpuProfile_result(getCpuProfile_result other) {
         if (other.isSetSuccess()) {
            this.success = other.success;
         }

      }

      public getCpuProfile_result deepCopy() {
         return new getCpuProfile_result(this);
      }

      public void clear() {
         this.success = null;
      }

      public String getSuccess() {
         return this.success;
      }

      public getCpuProfile_result setSuccess(String success) {
         this.success = success;
         return this;
      }

      public void unsetSuccess() {
         this.success = null;
      }

      public boolean isSetSuccess() {
         return this.success != null;
      }

      public void setSuccessIsSet(boolean value) {
         if (!value) {
            this.success = null;
         }

      }

      public void setFieldValue(_Fields field, Object value) {
         switch (field) {
            case SUCCESS:
               if (value == null) {
                  this.unsetSuccess();
               } else {
                  this.setSuccess((String)value);
               }
            default:
         }
      }

      public Object getFieldValue(_Fields field) {
         switch (field) {
            case SUCCESS:
               return this.getSuccess();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case SUCCESS:
                  return this.isSetSuccess();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         if (that == null) {
            return false;
         } else {
            return that instanceof getCpuProfile_result ? this.equals((getCpuProfile_result)that) : false;
         }
      }

      public boolean equals(getCpuProfile_result that) {
         if (that == null) {
            return false;
         } else {
            boolean this_present_success = this.isSetSuccess();
            boolean that_present_success = that.isSetSuccess();
            if (this_present_success || that_present_success) {
               if (!this_present_success || !that_present_success) {
                  return false;
               }

               if (!this.success.equals(that.success)) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         List<Object> list = new ArrayList();
         boolean present_success = this.isSetSuccess();
         list.add(present_success);
         if (present_success) {
            list.add(this.success);
         }

         return list.hashCode();
      }

      public int compareTo(getCpuProfile_result other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.valueOf(this.isSetSuccess()).compareTo(other.isSetSuccess());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSuccess()) {
                  lastComparison = TBaseHelper.compareTo(this.success, other.success);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      public _Fields fieldForId(int fieldId) {
         return FacebookService.getCpuProfile_result._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         ((SchemeFactory)schemes.get(iprot.getScheme())).getScheme().read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         ((SchemeFactory)schemes.get(oprot.getScheme())).getScheme().write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("getCpuProfile_result(");
         boolean first = true;
         sb.append("success:");
         if (this.success == null) {
            sb.append("null");
         } else {
            sb.append(this.success);
         }

         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      static {
         schemes.put(StandardScheme.class, new getCpuProfile_resultStandardSchemeFactory());
         schemes.put(TupleScheme.class, new getCpuProfile_resultTupleSchemeFactory());
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(FacebookService.getCpuProfile_result._Fields.SUCCESS, new FieldMetaData("success", (byte)3, new FieldValueMetaData((byte)11)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(getCpuProfile_result.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         SUCCESS((short)0, "success");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 0:
                  return SUCCESS;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class getCpuProfile_resultStandardSchemeFactory implements SchemeFactory {
         private getCpuProfile_resultStandardSchemeFactory() {
         }

         public getCpuProfile_resultStandardScheme getScheme() {
            return new getCpuProfile_resultStandardScheme();
         }
      }

      private static class getCpuProfile_resultStandardScheme extends StandardScheme {
         private getCpuProfile_resultStandardScheme() {
         }

         public void read(TProtocol iprot, getCpuProfile_result struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 0:
                     if (schemeField.type == 11) {
                        struct.success = iprot.readString();
                        struct.setSuccessIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, getCpuProfile_result struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(FacebookService.getCpuProfile_result.STRUCT_DESC);
            if (struct.success != null) {
               oprot.writeFieldBegin(FacebookService.getCpuProfile_result.SUCCESS_FIELD_DESC);
               oprot.writeString(struct.success);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class getCpuProfile_resultTupleSchemeFactory implements SchemeFactory {
         private getCpuProfile_resultTupleSchemeFactory() {
         }

         public getCpuProfile_resultTupleScheme getScheme() {
            return new getCpuProfile_resultTupleScheme();
         }
      }

      private static class getCpuProfile_resultTupleScheme extends TupleScheme {
         private getCpuProfile_resultTupleScheme() {
         }

         public void write(TProtocol prot, getCpuProfile_result struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetSuccess()) {
               oprot.writeString(struct.success);
            }

         }

         public void read(TProtocol prot, getCpuProfile_result struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.success = iprot.readString();
               struct.setSuccessIsSet(true);
            }

         }
      }
   }

   public static class aliveSince_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("aliveSince_args");
      private static final Map schemes = new HashMap();
      public static final Map metaDataMap;

      public aliveSince_args() {
      }

      public aliveSince_args(aliveSince_args other) {
      }

      public aliveSince_args deepCopy() {
         return new aliveSince_args(this);
      }

      public void clear() {
      }

      public void setFieldValue(_Fields field, Object value) {
         int var10000 = null.$SwitchMap$com$facebook$fb303$FacebookService$aliveSince_args$_Fields[field.ordinal()];
      }

      public Object getFieldValue(_Fields field) {
         int var10000 = null.$SwitchMap$com$facebook$fb303$FacebookService$aliveSince_args$_Fields[field.ordinal()];
         throw new IllegalStateException();
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            int var10000 = null.$SwitchMap$com$facebook$fb303$FacebookService$aliveSince_args$_Fields[field.ordinal()];
            throw new IllegalStateException();
         }
      }

      public boolean equals(Object that) {
         if (that == null) {
            return false;
         } else {
            return that instanceof aliveSince_args ? this.equals((aliveSince_args)that) : false;
         }
      }

      public boolean equals(aliveSince_args that) {
         return that != null;
      }

      public int hashCode() {
         List<Object> list = new ArrayList();
         return list.hashCode();
      }

      public int compareTo(aliveSince_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            return 0;
         }
      }

      public _Fields fieldForId(int fieldId) {
         return FacebookService.aliveSince_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         ((SchemeFactory)schemes.get(iprot.getScheme())).getScheme().read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         ((SchemeFactory)schemes.get(oprot.getScheme())).getScheme().write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("aliveSince_args(");
         boolean first = true;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      static {
         schemes.put(StandardScheme.class, new aliveSince_argsStandardSchemeFactory());
         schemes.put(TupleScheme.class, new aliveSince_argsTupleSchemeFactory());
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(aliveSince_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class aliveSince_argsStandardSchemeFactory implements SchemeFactory {
         private aliveSince_argsStandardSchemeFactory() {
         }

         public aliveSince_argsStandardScheme getScheme() {
            return new aliveSince_argsStandardScheme();
         }
      }

      private static class aliveSince_argsStandardScheme extends StandardScheme {
         private aliveSince_argsStandardScheme() {
         }

         public void read(TProtocol iprot, aliveSince_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
                     iprot.readFieldEnd();
               }
            }
         }

         public void write(TProtocol oprot, aliveSince_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(FacebookService.aliveSince_args.STRUCT_DESC);
            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class aliveSince_argsTupleSchemeFactory implements SchemeFactory {
         private aliveSince_argsTupleSchemeFactory() {
         }

         public aliveSince_argsTupleScheme getScheme() {
            return new aliveSince_argsTupleScheme();
         }
      }

      private static class aliveSince_argsTupleScheme extends TupleScheme {
         private aliveSince_argsTupleScheme() {
         }

         public void write(TProtocol prot, aliveSince_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
         }

         public void read(TProtocol prot, aliveSince_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
         }
      }
   }

   public static class aliveSince_result implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("aliveSince_result");
      private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte)10, (short)0);
      private static final Map schemes = new HashMap();
      public long success;
      private static final int __SUCCESS_ISSET_ID = 0;
      private byte __isset_bitfield;
      public static final Map metaDataMap;

      public aliveSince_result() {
         this.__isset_bitfield = 0;
      }

      public aliveSince_result(long success) {
         this();
         this.success = success;
         this.setSuccessIsSet(true);
      }

      public aliveSince_result(aliveSince_result other) {
         this.__isset_bitfield = 0;
         this.__isset_bitfield = other.__isset_bitfield;
         this.success = other.success;
      }

      public aliveSince_result deepCopy() {
         return new aliveSince_result(this);
      }

      public void clear() {
         this.setSuccessIsSet(false);
         this.success = 0L;
      }

      public long getSuccess() {
         return this.success;
      }

      public aliveSince_result setSuccess(long success) {
         this.success = success;
         this.setSuccessIsSet(true);
         return this;
      }

      public void unsetSuccess() {
         this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
      }

      public boolean isSetSuccess() {
         return EncodingUtils.testBit(this.__isset_bitfield, 0);
      }

      public void setSuccessIsSet(boolean value) {
         this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
      }

      public void setFieldValue(_Fields field, Object value) {
         switch (field) {
            case SUCCESS:
               if (value == null) {
                  this.unsetSuccess();
               } else {
                  this.setSuccess((Long)value);
               }
            default:
         }
      }

      public Object getFieldValue(_Fields field) {
         switch (field) {
            case SUCCESS:
               return this.getSuccess();
            default:
               throw new IllegalStateException();
         }
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            switch (field) {
               case SUCCESS:
                  return this.isSetSuccess();
               default:
                  throw new IllegalStateException();
            }
         }
      }

      public boolean equals(Object that) {
         if (that == null) {
            return false;
         } else {
            return that instanceof aliveSince_result ? this.equals((aliveSince_result)that) : false;
         }
      }

      public boolean equals(aliveSince_result that) {
         if (that == null) {
            return false;
         } else {
            boolean this_present_success = true;
            boolean that_present_success = true;
            if (this_present_success || that_present_success) {
               if (!this_present_success || !that_present_success) {
                  return false;
               }

               if (this.success != that.success) {
                  return false;
               }
            }

            return true;
         }
      }

      public int hashCode() {
         List<Object> list = new ArrayList();
         boolean present_success = true;
         list.add(present_success);
         if (present_success) {
            list.add(this.success);
         }

         return list.hashCode();
      }

      public int compareTo(aliveSince_result other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            lastComparison = Boolean.valueOf(this.isSetSuccess()).compareTo(other.isSetSuccess());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSuccess()) {
                  lastComparison = TBaseHelper.compareTo(this.success, other.success);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }

      public _Fields fieldForId(int fieldId) {
         return FacebookService.aliveSince_result._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         ((SchemeFactory)schemes.get(iprot.getScheme())).getScheme().read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         ((SchemeFactory)schemes.get(oprot.getScheme())).getScheme().write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("aliveSince_result(");
         boolean first = true;
         sb.append("success:");
         sb.append(this.success);
         first = false;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.__isset_bitfield = 0;
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      static {
         schemes.put(StandardScheme.class, new aliveSince_resultStandardSchemeFactory());
         schemes.put(TupleScheme.class, new aliveSince_resultTupleSchemeFactory());
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         tmpMap.put(FacebookService.aliveSince_result._Fields.SUCCESS, new FieldMetaData("success", (byte)3, new FieldValueMetaData((byte)10)));
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(aliveSince_result.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         SUCCESS((short)0, "success");

         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               case 0:
                  return SUCCESS;
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class aliveSince_resultStandardSchemeFactory implements SchemeFactory {
         private aliveSince_resultStandardSchemeFactory() {
         }

         public aliveSince_resultStandardScheme getScheme() {
            return new aliveSince_resultStandardScheme();
         }
      }

      private static class aliveSince_resultStandardScheme extends StandardScheme {
         private aliveSince_resultStandardScheme() {
         }

         public void read(TProtocol iprot, aliveSince_result struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  case 0:
                     if (schemeField.type == 10) {
                        struct.success = iprot.readI64();
                        struct.setSuccessIsSet(true);
                     } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                     }
                     break;
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
               }

               iprot.readFieldEnd();
            }
         }

         public void write(TProtocol oprot, aliveSince_result struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(FacebookService.aliveSince_result.STRUCT_DESC);
            if (struct.isSetSuccess()) {
               oprot.writeFieldBegin(FacebookService.aliveSince_result.SUCCESS_FIELD_DESC);
               oprot.writeI64(struct.success);
               oprot.writeFieldEnd();
            }

            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class aliveSince_resultTupleSchemeFactory implements SchemeFactory {
         private aliveSince_resultTupleSchemeFactory() {
         }

         public aliveSince_resultTupleScheme getScheme() {
            return new aliveSince_resultTupleScheme();
         }
      }

      private static class aliveSince_resultTupleScheme extends TupleScheme {
         private aliveSince_resultTupleScheme() {
         }

         public void write(TProtocol prot, aliveSince_result struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
            BitSet optionals = new BitSet();
            if (struct.isSetSuccess()) {
               optionals.set(0);
            }

            oprot.writeBitSet(optionals, 1);
            if (struct.isSetSuccess()) {
               oprot.writeI64(struct.success);
            }

         }

         public void read(TProtocol prot, aliveSince_result struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
               struct.success = iprot.readI64();
               struct.setSuccessIsSet(true);
            }

         }
      }
   }

   public static class reinitialize_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("reinitialize_args");
      private static final Map schemes = new HashMap();
      public static final Map metaDataMap;

      public reinitialize_args() {
      }

      public reinitialize_args(reinitialize_args other) {
      }

      public reinitialize_args deepCopy() {
         return new reinitialize_args(this);
      }

      public void clear() {
      }

      public void setFieldValue(_Fields field, Object value) {
         int var10000 = null.$SwitchMap$com$facebook$fb303$FacebookService$reinitialize_args$_Fields[field.ordinal()];
      }

      public Object getFieldValue(_Fields field) {
         int var10000 = null.$SwitchMap$com$facebook$fb303$FacebookService$reinitialize_args$_Fields[field.ordinal()];
         throw new IllegalStateException();
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            int var10000 = null.$SwitchMap$com$facebook$fb303$FacebookService$reinitialize_args$_Fields[field.ordinal()];
            throw new IllegalStateException();
         }
      }

      public boolean equals(Object that) {
         if (that == null) {
            return false;
         } else {
            return that instanceof reinitialize_args ? this.equals((reinitialize_args)that) : false;
         }
      }

      public boolean equals(reinitialize_args that) {
         return that != null;
      }

      public int hashCode() {
         List<Object> list = new ArrayList();
         return list.hashCode();
      }

      public int compareTo(reinitialize_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            return 0;
         }
      }

      public _Fields fieldForId(int fieldId) {
         return FacebookService.reinitialize_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         ((SchemeFactory)schemes.get(iprot.getScheme())).getScheme().read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         ((SchemeFactory)schemes.get(oprot.getScheme())).getScheme().write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("reinitialize_args(");
         boolean first = true;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      static {
         schemes.put(StandardScheme.class, new reinitialize_argsStandardSchemeFactory());
         schemes.put(TupleScheme.class, new reinitialize_argsTupleSchemeFactory());
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(reinitialize_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class reinitialize_argsStandardSchemeFactory implements SchemeFactory {
         private reinitialize_argsStandardSchemeFactory() {
         }

         public reinitialize_argsStandardScheme getScheme() {
            return new reinitialize_argsStandardScheme();
         }
      }

      private static class reinitialize_argsStandardScheme extends StandardScheme {
         private reinitialize_argsStandardScheme() {
         }

         public void read(TProtocol iprot, reinitialize_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
                     iprot.readFieldEnd();
               }
            }
         }

         public void write(TProtocol oprot, reinitialize_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(FacebookService.reinitialize_args.STRUCT_DESC);
            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class reinitialize_argsTupleSchemeFactory implements SchemeFactory {
         private reinitialize_argsTupleSchemeFactory() {
         }

         public reinitialize_argsTupleScheme getScheme() {
            return new reinitialize_argsTupleScheme();
         }
      }

      private static class reinitialize_argsTupleScheme extends TupleScheme {
         private reinitialize_argsTupleScheme() {
         }

         public void write(TProtocol prot, reinitialize_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
         }

         public void read(TProtocol prot, reinitialize_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
         }
      }
   }

   public static class shutdown_args implements TBase, Serializable, Cloneable, Comparable {
      private static final TStruct STRUCT_DESC = new TStruct("shutdown_args");
      private static final Map schemes = new HashMap();
      public static final Map metaDataMap;

      public shutdown_args() {
      }

      public shutdown_args(shutdown_args other) {
      }

      public shutdown_args deepCopy() {
         return new shutdown_args(this);
      }

      public void clear() {
      }

      public void setFieldValue(_Fields field, Object value) {
         int var10000 = null.$SwitchMap$com$facebook$fb303$FacebookService$shutdown_args$_Fields[field.ordinal()];
      }

      public Object getFieldValue(_Fields field) {
         int var10000 = null.$SwitchMap$com$facebook$fb303$FacebookService$shutdown_args$_Fields[field.ordinal()];
         throw new IllegalStateException();
      }

      public boolean isSet(_Fields field) {
         if (field == null) {
            throw new IllegalArgumentException();
         } else {
            int var10000 = null.$SwitchMap$com$facebook$fb303$FacebookService$shutdown_args$_Fields[field.ordinal()];
            throw new IllegalStateException();
         }
      }

      public boolean equals(Object that) {
         if (that == null) {
            return false;
         } else {
            return that instanceof shutdown_args ? this.equals((shutdown_args)that) : false;
         }
      }

      public boolean equals(shutdown_args that) {
         return that != null;
      }

      public int hashCode() {
         List<Object> list = new ArrayList();
         return list.hashCode();
      }

      public int compareTo(shutdown_args other) {
         if (!this.getClass().equals(other.getClass())) {
            return this.getClass().getName().compareTo(other.getClass().getName());
         } else {
            int lastComparison = 0;
            return 0;
         }
      }

      public _Fields fieldForId(int fieldId) {
         return FacebookService.shutdown_args._Fields.findByThriftId(fieldId);
      }

      public void read(TProtocol iprot) throws TException {
         ((SchemeFactory)schemes.get(iprot.getScheme())).getScheme().read(iprot, this);
      }

      public void write(TProtocol oprot) throws TException {
         ((SchemeFactory)schemes.get(oprot.getScheme())).getScheme().write(oprot, this);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("shutdown_args(");
         boolean first = true;
         sb.append(")");
         return sb.toString();
      }

      public void validate() throws TException {
      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         try {
            this.write(new TCompactProtocol(new TIOStreamTransport(out)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         try {
            this.read(new TCompactProtocol(new TIOStreamTransport(in)));
         } catch (TException te) {
            throw new IOException(te);
         }
      }

      static {
         schemes.put(StandardScheme.class, new shutdown_argsStandardSchemeFactory());
         schemes.put(TupleScheme.class, new shutdown_argsTupleSchemeFactory());
         Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
         metaDataMap = Collections.unmodifiableMap(tmpMap);
         FieldMetaData.addStructMetaDataMap(shutdown_args.class, metaDataMap);
      }

      public static enum _Fields implements TFieldIdEnum {
         private static final Map byName = new HashMap();
         private final short _thriftId;
         private final String _fieldName;

         public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
               default:
                  return null;
            }
         }

         public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) {
               throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            } else {
               return fields;
            }
         }

         public static _Fields findByName(String name) {
            return (_Fields)byName.get(name);
         }

         private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
         }

         public short getThriftFieldId() {
            return this._thriftId;
         }

         public String getFieldName() {
            return this._fieldName;
         }

         static {
            for(_Fields field : EnumSet.allOf(_Fields.class)) {
               byName.put(field.getFieldName(), field);
            }

         }
      }

      private static class shutdown_argsStandardSchemeFactory implements SchemeFactory {
         private shutdown_argsStandardSchemeFactory() {
         }

         public shutdown_argsStandardScheme getScheme() {
            return new shutdown_argsStandardScheme();
         }
      }

      private static class shutdown_argsStandardScheme extends StandardScheme {
         private shutdown_argsStandardScheme() {
         }

         public void read(TProtocol iprot, shutdown_args struct) throws TException {
            iprot.readStructBegin();

            while(true) {
               TField schemeField = iprot.readFieldBegin();
               if (schemeField.type == 0) {
                  iprot.readStructEnd();
                  struct.validate();
                  return;
               }

               switch (schemeField.id) {
                  default:
                     TProtocolUtil.skip(iprot, schemeField.type);
                     iprot.readFieldEnd();
               }
            }
         }

         public void write(TProtocol oprot, shutdown_args struct) throws TException {
            struct.validate();
            oprot.writeStructBegin(FacebookService.shutdown_args.STRUCT_DESC);
            oprot.writeFieldStop();
            oprot.writeStructEnd();
         }
      }

      private static class shutdown_argsTupleSchemeFactory implements SchemeFactory {
         private shutdown_argsTupleSchemeFactory() {
         }

         public shutdown_argsTupleScheme getScheme() {
            return new shutdown_argsTupleScheme();
         }
      }

      private static class shutdown_argsTupleScheme extends TupleScheme {
         private shutdown_argsTupleScheme() {
         }

         public void write(TProtocol prot, shutdown_args struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol)prot;
         }

         public void read(TProtocol prot, shutdown_args struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol)prot;
         }
      }
   }

   public interface AsyncIface {
      void getName(AsyncMethodCallback var1) throws TException;

      void getVersion(AsyncMethodCallback var1) throws TException;

      void getStatus(AsyncMethodCallback var1) throws TException;

      void getStatusDetails(AsyncMethodCallback var1) throws TException;

      void getCounters(AsyncMethodCallback var1) throws TException;

      void getCounter(String var1, AsyncMethodCallback var2) throws TException;

      void setOption(String var1, String var2, AsyncMethodCallback var3) throws TException;

      void getOption(String var1, AsyncMethodCallback var2) throws TException;

      void getOptions(AsyncMethodCallback var1) throws TException;

      void getCpuProfile(int var1, AsyncMethodCallback var2) throws TException;

      void aliveSince(AsyncMethodCallback var1) throws TException;

      void reinitialize(AsyncMethodCallback var1) throws TException;

      void shutdown(AsyncMethodCallback var1) throws TException;
   }

   public interface Iface {
      String getName() throws TException;

      String getVersion() throws TException;

      fb_status getStatus() throws TException;

      String getStatusDetails() throws TException;

      Map getCounters() throws TException;

      long getCounter(String var1) throws TException;

      void setOption(String var1, String var2) throws TException;

      String getOption(String var1) throws TException;

      Map getOptions() throws TException;

      String getCpuProfile(int var1) throws TException;

      long aliveSince() throws TException;

      void reinitialize() throws TException;

      void shutdown() throws TException;
   }
}
