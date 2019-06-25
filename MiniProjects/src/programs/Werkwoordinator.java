package programs;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Werkwoordinator extends Application {
	
	public void launchProperly() {
		Application.launch();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		TextArea inputTextArea = new TextArea();
		TextArea outputTextArea = new TextArea();
		Button translateButton = new Button("Werkwoordanizeer input");
		
		inputTextArea.setPromptText("Voer hier een zelfstandignaamwoord in");
		
		outputTextArea.setEditable(false);
		outputTextArea.setPromptText("output verschijnt hier");
		
		translateButton.setOnAction(event -> {
			String result = "";
			
			String[] words = inputTextArea.getText().split(" ");
			
			for (String word : words) {
				
				if (word.length() < 1)
					continue;
				
				// prefix het resultaat met "ge" als het woord niet met een klinker start, als het wel met een klinker start dan wordt de prefix "ge-"
				String prefix = "ge";
				if ("aeiou".contains("" + word.charAt(0)))
					prefix = "ge-";
				
				String postfix = "";
				
				if ("aeiou".contains("" + word.toLowerCase().charAt(word.length()-1)))
					postfix += "'";
				if (word.toLowerCase().charAt(word.length()-1) != 't')
					postfix += "t";
				
				result += prefix + word + postfix + " ";
			}
			
			outputTextArea.setText(result);
		});
		
		
		// build the UI and show it
		GridPane gp = new GridPane();
		gp.add(translateButton, 0, 0);
		gp.add(inputTextArea, 1, 0);
		gp.add(outputTextArea, 1, 1);
		
		primaryStage.setScene(new Scene(gp, 600, 600));
		primaryStage.setTitle("Werkwoordinator - by Joeri");
		primaryStage.show();
	}
}
