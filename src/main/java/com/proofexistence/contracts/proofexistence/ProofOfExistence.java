package com.proofexistence.contracts;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.9.
 */
@SuppressWarnings("rawtypes")
public class ProofOfExistence extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b5061011d806100206000396000f30060806040526004361060485763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416637a0218a28114604d578063d8ae15f9146064575b600080fd5b348015605857600080fd5b506062600435608b565b005b348015606f57600080fd5b50607960043560df565b60408051918252519081900360200190f35b600081815260208190526040902054151560b15760008181526020819052604090204290555b60405181907f12750f43e05655489f35f85ec76bec70d7da33b4141faee035fc949ac123a86490600090a250565b600060208190529081526040902054815600a165627a7a7230582081aec544daf79f49c7aaef53922d6b758ab551d28ab2a6433568162f179eb51c0029";

    public static final String FUNC_STAMPDOCUMENT = "stampDocument";

    public static final String FUNC_STAMPS = "stamps";

    public static final Event DOCUMENTSTAMPED_EVENT = new Event("DocumentStamped", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}));
    ;

    @Deprecated
    protected ProofOfExistence(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ProofOfExistence(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ProofOfExistence(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ProofOfExistence(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> stampDocument(byte[] documentHash) {
        final Function function = new Function(
                FUNC_STAMPDOCUMENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(documentHash)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> stamps(byte[] param0) {
        final Function function = new Function(FUNC_STAMPS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public List<DocumentStampedEventResponse> getDocumentStampedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(DOCUMENTSTAMPED_EVENT, transactionReceipt);
        ArrayList<DocumentStampedEventResponse> responses = new ArrayList<DocumentStampedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DocumentStampedEventResponse typedResponse = new DocumentStampedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.documentHash = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<DocumentStampedEventResponse> documentStampedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, DocumentStampedEventResponse>() {
            @Override
            public DocumentStampedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(DOCUMENTSTAMPED_EVENT, log);
                DocumentStampedEventResponse typedResponse = new DocumentStampedEventResponse();
                typedResponse.log = log;
                typedResponse.documentHash = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<DocumentStampedEventResponse> documentStampedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DOCUMENTSTAMPED_EVENT));
        return documentStampedEventFlowable(filter);
    }

    @Deprecated
    public static ProofOfExistence load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ProofOfExistence(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ProofOfExistence load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ProofOfExistence(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ProofOfExistence load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ProofOfExistence(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ProofOfExistence load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ProofOfExistence(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ProofOfExistence> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ProofOfExistence.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ProofOfExistence> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ProofOfExistence.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<ProofOfExistence> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ProofOfExistence.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ProofOfExistence> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ProofOfExistence.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class DocumentStampedEventResponse extends BaseEventResponse {
        public byte[] documentHash;
    }
}
