package domain;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * A single message object. Many will make a chat or a wall (in groups)
 */
@DatabaseTable(tableName = "Messages")
public class Message {
	
	/** id of the message */
	@DatabaseField( generatedId = true)
	private String msgId;
	
	/** text of the message */ 
	@DatabaseField
	private String text;
	
	/** empty constructor for persistence usage */ 
	public Message(){
	}
	
	/**
	 * @param text contained in the message being instanced
	 */
	public Message(String text){
		this.text = text;
	}
	
	/**
	 * Converts the message to upercase
	 */
	public void uperCaseMessage(){
		this.text = this.text.toUpperCase();
	}
	
	/**
	 * converts the message to lowerCase
	 */
	public void lowerCaseMessage(){
		this.text = this.text.toLowerCase();
	}
	
	/**
	 * @param word to be checked
	 * @return boolean value indicating if the word is contained in the message
	 */
	public boolean containsWordInsideMessage(String word){
		return this.text.contains(word);
	}
	
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @return the id
	 */
	public String getMsgId() {
		return this.msgId;
	}

}
