pragma solidity ^0.4.26;

contract ProofOfExistence {
    
  mapping (bytes32 => uint) public stamps;
  
  event DocumentStamped(bytes32 indexed documentHash);

  function stampDocument(bytes32 documentHash) public {
    if (stamps[documentHash] == 0) {
        stamps[documentHash] = block.timestamp;
    }
    emit DocumentStamped(documentHash);
  }

}