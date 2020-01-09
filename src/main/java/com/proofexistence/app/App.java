package com.proofexistence.app;

import java.math.BigInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.besu.Besu;
import org.web3j.crypto.Credentials;
import org.web3j.utils.Base64String;
import org.web3j.tx.gas.BesuPrivacyGasProvider;
import org.web3j.tx.BesuPrivateTransactionManager;
import org.web3j.protocol.besu.response.privacy.PrivGetTransactionReceipt;
import com.proofexistence.contracts.ProofOfExistence;

public class App {

    private Besu besu;
    private BigInteger gasPrice = BigInteger.valueOf(0);
    private BigInteger gasLimit = BigInteger.valueOf(4000000);
    private int retries = 5;
    private Credentials credentials;
    private Base64String privacyGroupId;
    private Base64String privateFrom;
    private String networkId;
    private BesuPrivateTransactionManager transactionManager;
    private static final Logger log = LoggerFactory.getLogger(App.class);

    public App(String host, String privateKey, String privGroupId, String privFrom) {
        try {
            besu = Besu.build(new HttpService(host));
            credentials = Credentials.create(privateKey);
            privacyGroupId = Base64String.wrap(privGroupId);
            privateFrom = Base64String.wrap(privFrom);
            networkId = besu.netVersion().send().getResult();
            transactionManager = new BesuPrivateTransactionManager(besu, getDefaultBesuPrivacyGasProvider(), credentials, Long.parseLong(networkId), privateFrom, privacyGroupId);
        } catch (Exception e) {
            log.info("Error: " + e.getMessage());
        }
    }

    BesuPrivacyGasProvider getDefaultBesuPrivacyGasProvider() {
		BesuPrivacyGasProvider besuPrivacyGasProvider = new BesuPrivacyGasProvider(gasPrice, gasLimit);
		return besuPrivacyGasProvider;
    }

    private String sendPrivTransaction(String contractAddress, String encodeFunction) throws Exception {
		String hash = transactionManager.sendTransaction(gasPrice, gasLimit, contractAddress, encodeFunction, BigInteger.valueOf(0), false).getTransactionHash();
		PrivGetTransactionReceipt receipt = getPrivTransactionReceipt(hash, retries);
		if (receipt == null) throw new Exception("Transaction not mined");
		return hash;
    }
    
    PrivGetTransactionReceipt getPrivTransactionReceipt(String txHash, int retries) throws Exception {
		PrivGetTransactionReceipt transactionReceipt = null;
		int cont = 0;
		while (cont <= retries) {
			transactionReceipt = besu.privGetTransactionReceipt(txHash).send();
			if (transactionReceipt.getResult() != null) {
				break;
			}
			cont++;
			Thread.sleep(1 * 1000);
		}
		return transactionReceipt;
    }
    
    public static byte[] stringToBytes(String string, int lenght) {
		byte[] byteValue = string.getBytes();
		byte[] byteValueLen = new byte[lenght];
		System.arraycopy(byteValue, 0, byteValueLen, 0, lenght);
		return byteValueLen;
	}
    
    public String deployContract() throws Exception {
        ProofOfExistence contract = ProofOfExistence.deploy(besu, transactionManager, getDefaultBesuPrivacyGasProvider()).send();
        return contract.getContractAddress();
    }

    public String stampDocument(String contractAddress, String hash) throws Exception {
        ProofOfExistence contract = ProofOfExistence.load(contractAddress, besu, transactionManager, getDefaultBesuPrivacyGasProvider());
        String encodeFunction = contract.stampDocument(stringToBytes(hash, 32)).encodeFunctionCall();
        String txHash = sendPrivTransaction(contractAddress, encodeFunction);
        return hash;
    }

    public BigInteger getTimeDocument(String contractAddress, String hash) throws Exception {
        ProofOfExistence contract = ProofOfExistence.load(contractAddress, besu, transactionManager, getDefaultBesuPrivacyGasProvider());
        BigInteger timeDocument = contract.stamps(stringToBytes(hash, 32)).send();
        return timeDocument;
    }

}