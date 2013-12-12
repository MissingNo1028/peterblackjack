package com.example.blackjack;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button1 = (Button) findViewById(R.id.start);
		View.OnClickListener buttonClickListener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Add logic for button
				startGame(v);
				
			}
		};
		
		button1.setOnClickListener(buttonClickListener);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    

	public void startGame(View view){
		Intent myIntent = new Intent(this, BlackjackGame.class);
		int chips = 200;
		myIntent.putExtra("chips", chips);
	    startActivity(myIntent);
	}
}
