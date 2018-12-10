package pstu;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.HashMap;
import java.util.Map;

public class Controller {

	private static final int ENCRYPTION_SHIFT = 8;
	private static final int ALPHABET_COUNT = 32;
	@FXML
	public TextArea sourceArea;
	@FXML
	public TextArea encryptedArea;
	@FXML
	public Button decryptButton;

	private HashMap<Character, Integer> encryptionAlphabets = new HashMap<>();
	private Scene scene;

	void setScene(Scene scene) {
		this.scene = scene;
	}

	private void readCode() {
		encryptionAlphabets.clear();
		for (int i = 1; i < 33; i++) {
			TextField tf = (TextField) scene.lookup("#tf" + i);
			encryptionAlphabets.put(tf.getText().charAt(0), i);
		}
	}

	private String encrypt() {
		StringBuilder sb = new StringBuilder();
		for (char c : sourceArea.getText().toCharArray()) {
			Integer  alphabetPosition  = encryptionAlphabets.get(c);
			int encryptedPosition = ALPHABET_COUNT;
			if (alphabetPosition + ENCRYPTION_SHIFT != ALPHABET_COUNT){
				encryptedPosition = (alphabetPosition + ENCRYPTION_SHIFT) % ALPHABET_COUNT;
			}
			for (Map.Entry<Character, Integer> characterIntegerEntry : encryptionAlphabets.entrySet()) {
				if(characterIntegerEntry.getValue().equals(encryptedPosition)) {
					sb.append(characterIntegerEntry.getKey());
				}
			}
		}
		return sb.toString();
	}

	private String decrypt() {
		StringBuilder sb = new StringBuilder();
		for (char c : encryptedArea.getText().toCharArray()) {
			Integer  alphabetPosition  = encryptionAlphabets.get(c);
			int encryptedPosition = Math.abs(alphabetPosition - ENCRYPTION_SHIFT);
			if (alphabetPosition <= ENCRYPTION_SHIFT) {
				encryptedPosition = ALPHABET_COUNT - encryptedPosition;
			}
			for (Map.Entry<Character, Integer> characterIntegerEntry : encryptionAlphabets.entrySet()) {
				if(characterIntegerEntry.getValue().equals(encryptedPosition)) {
					sb.append(characterIntegerEntry.getKey());
				}
			}
		}
		return sb.toString();
	}

	public void encryptAction() {
		readCode();
		encryptedArea.setText(encrypt());
	}

	public void decryptAction()  {
		readCode();
		sourceArea.setText(decrypt());
	}
}


