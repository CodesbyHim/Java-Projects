package finalproject;



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Calculator extends Application
{
	
	private TextField  textField= new TextField();
	private double num1 =0;
	private  String op= "";
	private boolean start = true;
	public static void main(String[] args) 
	{
		
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception 
	{
		// step 1 create button objects
	
	textField.setFont(Font.font(20));
	textField.setPrefHeight(50);
	textField.setAlignment(Pos.CENTER_RIGHT);
	textField.setEditable(false);
	
	StackPane stackpane=new StackPane();
	stackpane.setPadding(new Insets(10));
	stackpane.getChildren().add(textField);
	
	// step 2 create layout object 
	TilePane tile = new TilePane(); // tilePane lay out
	tile.setHgap(10);
    tile.setVgap(10);
    tile.setAlignment(Pos.TOP_CENTER);

    tile.getChildren().addAll(
    		
    		createButtonForClear("AC"),
    		createButtonForOperators("%"),
    		createButtonForDecimal("."),	
    		createButtonForOperators("/"),
    		
    		createButtonForNumber("7"),
    		createButtonForNumber("8"),
    		createButtonForNumber("9"),
    		createButtonForOperators("X"),
    		
    		createButtonForNumber("4"),
    		createButtonForNumber("5"),
    		createButtonForNumber("6"),
    		createButtonForOperators("-"),
    	    		
    		createButtonForNumber("1"),
    		createButtonForNumber("2"),
    		createButtonForNumber("3"),
    		createButtonForOperators("+"),
    		    		
    		createButtonForNumber("00"),
    		createButtonForNumber("0"),
    		createButtonForBackspace("B"),  
    	    createButtonForOperators("=") 
    		 		
    		);
    // step 3 create scene object
    
    BorderPane borderPane = new BorderPane();
	borderPane.setTop(stackpane);
	borderPane.setCenter(tile);
    
    
    
    Scene scene=new Scene(borderPane,250,373);
    // step 4 set tile at scene
    stage.setScene(scene);
    stage.setTitle("RICR Calculator");
    stage.setResizable(false);
    stage.show();

	}
	
		private Button createButtonForNumber(String ch) 
		{	
		Button button = new Button(ch);
		button.setFont(Font.font(18));
		button.setPrefSize(50,50);
		button.setOnAction(this ::processNumbers);
		return button;
		}
		
		private Button createButtonForClear(String ch) 
		{	
		Button button = new Button(ch);
		button.setFont(Font.font(18));
		button.setPrefSize(50,50);
		return button;
		}

		private Button createButtonForOperators(String ch) 
		{	
		Button button = new Button(ch);
		button.setFont(Font.font(18));
		button.setPrefSize(50,50);
		if (ch.equals("=")) 
		{
	      button.setStyle("-fx-background-color: skyblue;");
	    }
		
		button.setOnAction(this::processOperators);
		
		return button;
		}
		private Button createButtonForDecimal(String ch) 
		{	
		Button button = new Button(ch);
		button.setFont(Font.font(18));
		button.setPrefSize(50,50);
		return button;
		}
	
		private Button createButtonForBackspace(String ch) 
		{	
		Button button = new Button(ch);
		button.setFont(Font.font(18));
		button.setPrefSize(50,50);
		return button;
		}
	
		
		private void processNumbers(ActionEvent e) 
		{
			
			if(start) 
			{
				textField.setText("");
				start=false;
			}

			String value=((Button)e.getSource()).getText();
			textField.setText(textField.getText()+ value);
		}
		
		
		private void processOperators(ActionEvent e) 
		{
			String value=((Button)e.getSource()).getText();
			
			//if user enter other operators than '='
			if(!value.equals("=")) 
			{
				if(!op.isEmpty())
				{
				return;
				}
				num1=Double.parseDouble(textField.getText());
				op=value;
				textField.setText("");
				
			}
			
			//if user enter operator '='
			else 
			{
				if(op.isEmpty())
					return;
				double num2=Double.parseDouble(textField.getText());
				double result = calculate(num1,num2,op);
				textField.setText(String.valueOf(result));
				start=true;
				op="";
			}
			
		}
		
		
		private double calculate(double num1,double num2, String operator) {
			switch(operator) {
			case "+":
				return num1+num2;
			case "-":
				return num1-num2;
			case "X":
				return num1*num2;
				
			case "%":
				return num1%num2;
			case "/":
				if(num2==0)
					return 0;
		         return num1/num2;
		         
			default: 
				return 0;
				
			}
		}



	
	
	
	

}
