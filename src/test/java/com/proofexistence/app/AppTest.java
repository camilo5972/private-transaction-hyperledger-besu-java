package com.proofexistence.app;

import org.junit.Test;
import static org.junit.Assert.*;

import java.math.BigInteger;

import static org.hamcrest.CoreMatchers.containsString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppTest {

    private static final Logger log = LoggerFactory.getLogger(AppTest.class);
    private String host = "http://localhost:20000"; // nodo besu 1
    private String privateKey = "0xa4db817db6c69ef95682d2f9e60d46a3e6cd890f0f3d8226cade1711584fb313"; // Address => 0xafe7a39a573c49cce63a2294c74c2e8dde39d86a
    private String privGroupId = "LOO+4JomlfJ3Q+SlaOkxRJkN9YCLeBPdxlf2M1+oc+A=";
    private String privFrom = "A1aVtMxLCUHmBVHXoZzzBgPbW/wj5axDpW9X8l91SGo="; // Orion 1
    private App app = new App(host, privateKey, privGroupId, privFrom);

    private String hash = "0x3fd54831f488a22b28398de0c567a3";
    private String contractAddress = "0x2cd5ef75c0dbe8034a609304aeec33083ea67814";

    //@Test
	public void deployContract() throws Exception {
        String contractAddress = app.deployContract();
        log.info("Address: " + contractAddress);
		assertThat(contractAddress, containsString("0x"));
    }
    
    //@Test
    public void stampDocument() throws Exception {
        String txHash = app.stampDocument(contractAddress, hash);
        log.info("txHash: " + txHash);
        assertThat(contractAddress, containsString("0x"));
    }

    //@Test
    public void getTimeDocument() throws Exception {
        BigInteger timeDocument = app.getTimeDocument(contractAddress, hash);
        log.info("timeDocuement: " + timeDocument.intValue());
        assertTrue(timeDocument.intValue() > 0);
    }

}