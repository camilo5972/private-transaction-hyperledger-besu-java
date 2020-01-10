# Private transactions Hyperledger Besu

Java example library with Maven for interaction with smart contract using private transactions with Hyperledger Besu. This is done using a configuration with Orion, which is responsible for managing private transactions with the Besu node. This example is based on a configuration with 3 Besu nodes and 3 Orion nodes respectively from the [besu quickstart](https://github.com/PegaSysEng/besu-quickstart)(with privacy), of which 2 are used to generate a group identifier ([privacyGroupId](https://besu.hyperledger.org/en/stable/Concepts/Privacy/Privacy-Groups/)).

# Run tests

Before running the tests, the following must be configured in ``AppTest.java`` file:

```javascript
private String host = "http://localhost:20000"; // Besu node IP
private String privateKey = "0xa4db817db6c69ef95682d2f9e60d46a3e6cd890f0f3d8226cade1711584fb313"; // Private key to make transactions
private String privGroupId = "LOO+4JomlfJ3Q+SlaOkxRJkN9YCLeBPdxlf2M1+oc+A="; // Privacy group ID. You need to generate your own privGroupId
private String privFrom = "A1aVtMxLCUHmBVHXoZzzBgPbW/wj5axDpW9X8l91SGo="; // Public key of the Orion node
```

[How to generate privGroupId](https://besu.hyperledger.org/en/stable/Reference/API-Methods/#priv_createprivacygroup)

To test the library you must uncomment each of the tests. Run first the test to deploy a contract, then to stamp a docuement and finally to verify. The address of the deployed contract is placed in the ``contractAddress`` variable before running the other. tests

```sh
$ mvn clean test
```