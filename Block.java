package cripto;

import java.util.Date;

public class Block {
	
	public String hash;
	public String previousHash; 
	private String data; //our data will be a simple message.
	private long timeStamp; //as number of milliseconds since 1/1/1970.
	private int nonce;
	private long minedTime; //added to calculate mined time.
	
	//Block Constructor.  
	public Block(String data,String previousHash ) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		
		this.hash = calculateHash(); //Making sure we do this after we set the other values.
	}
	
	//Calculate new hash based on blocks contents
	public String calculateHash() {
		String calculatedhash = StringUtil.applySha256( 
				previousHash +
				Long.toString(timeStamp) +
				Integer.toString(nonce) + 
				data 
				);
		return calculatedhash;
	}
	
	public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0" 
		while(!hash.substring( 0, difficulty).equals(target)) {
//			nonce ++;
			nonce = (int)(Math.random()*999999999); // generate random number beetven 0 to 9 mil.
			hash = calculateHash();
		}
		minedTime = new Date().getTime() - timeStamp;
		System.out.println("Block Mined!!! : " + hash);
		System.out.println("Block mined in : " + minedTime + " milliseconds.");
	}
	

}