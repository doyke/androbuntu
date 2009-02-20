package com.android.AndroBuntu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.android.AndroBuntu.R;



import com.android.AndroBuntu.ScriptList;

public class AndroBuntu extends Activity implements View.OnClickListener {

	private EditText et;

    private String[] mStrings = {
            "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
            "Acorn"
    };
    
    
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       TextView tv = new TextView(this);
       et = new EditText(this);
       
       tv.setText( R.string.goodbye );
       
       et.setText( "192.168.0.9" );
       
       Button button = new Button(this);
       button.setOnClickListener(this);
       button.setText("Mute");


       Button x10_button = new Button(this);
       x10_button.setOnClickListener(x10_button_listener);
       x10_button.setText("x10 Controls");
      
       Button scripts_button = new Button(this);
       scripts_button.setOnClickListener(scripts_button_listener);
       scripts_button.setText("Scripts");
        
        LinearLayout myfoo = new LinearLayout(this);
        myfoo.setOrientation(LinearLayout.VERTICAL);
        LinearLayout myfoo2 = new LinearLayout(this);
        myfoo2.setOrientation(LinearLayout.HORIZONTAL);
//        myfoo2.setGravity(Gravity.FILL);	// FIXME: Useless
        
        
        
        Button voldown_button = new Button(this);

//        voldown_button.setGravity(Gravity.FILL);
        voldown_button.setOnClickListener(voldown_listener);
        voldown_button.setText("VolDown");
        myfoo2.addView(voldown_button);
        
        Button volup_button = new Button(this);
//        volup_button.setGravity(Gravity.FILL);
        volup_button.setOnClickListener(volup_listener);
        volup_button.setText("VolUp");
        myfoo2.addView(volup_button);
        




        myfoo.addView(myfoo2);
        
        Button screen_blank_button = new Button(this);
        screen_blank_button.setOnClickListener(screen_blank_listener);
        screen_blank_button.setText("Blank Screen");
        myfoo.addView(screen_blank_button);
        
        myfoo.addView(x10_button);
        myfoo.addView(scripts_button);
        
        myfoo.addView(tv);
        myfoo.addView(et);
        myfoo.addView(button);
       
        
        ListView lv = new ListView(this);
        lv.setAdapter( new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mStrings) );
       
        myfoo.addView(lv);
        
       setContentView(myfoo);
   }
   
   
   private View.OnClickListener screen_blank_listener = new View.OnClickListener() {
	    public void onClick(View v) {
	 	   String reply = send_message("screen_blank");
		   Toast.makeText(AndroBuntu.this, reply, Toast.LENGTH_SHORT).show();
	    }
	};
	
   private View.OnClickListener voldown_listener = new View.OnClickListener() {
	    public void onClick(View v) {
	 	   String reply = send_message("XF86AudioLowerVolume");
		   Toast.makeText(AndroBuntu.this, reply, Toast.LENGTH_SHORT).show();
	    }
	};

   private View.OnClickListener volup_listener = new View.OnClickListener() {
	    public void onClick(View v) {
	 	   String reply = send_message("XF86AudioRaiseVolume");
		   Toast.makeText(AndroBuntu.this, reply, Toast.LENGTH_SHORT).show();
	    }
	};
   
   private View.OnClickListener x10_button_listener = new View.OnClickListener() {
	    public void onClick(View v) {
	      // do something when the button is clicked
	    }
	};

   private View.OnClickListener scripts_button_listener = new View.OnClickListener() {
	    public void onClick(View v) {
	    	
	    	
	    	ScriptList s = new ScriptList();
	    	
	    	// TODO: fark
	    }
	};
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

		
		menu.add("Server Options");
		menu.add("Frankenstein");

		return true;

    }
    
    public boolean  onOptionsItemSelected  (MenuItem item)  {
    	
    	String reply = send_message("greet");
    	Toast.makeText(AndroBuntu.this, item.getTitle() + ": "+reply, Toast.LENGTH_SHORT).show();
 	   
		return true;
    
    }
	
	
   private String send_message(String command) {
	   
	   	String message = "";
	    try {

	    	String farkwad = et.getText().toString();
	    	InetAddress addr = InetAddress.getByName( farkwad );
	    	Socket MyClient = new Socket(addr, 46645);
	    	
	        try {
	        	BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(MyClient.getOutputStream()));   
		        BufferedReader rd = new BufferedReader(new InputStreamReader(MyClient.getInputStream()));
		    	
	            wr.write( command );
	            wr.flush();
	    	
		    	
		        String str;
		        while ((str = rd.readLine()) != null)
		        	message += str;


		        rd.close();	
				wr.close();
				
	        } catch (IOException e) {
		    	Log.d("fark", "The buffered reader/writer failed somehow...");
	        }
	
			MyClient.close();
			
	    }
	    catch (IOException e) {
	        System.out.println(e);
	        
	        message = "Failed.";
	    }
	    return message;
   }
   
   
   public void onClick(View v) {
	
	   String reply = send_message("XF86AudioMute");
	   Toast.makeText(AndroBuntu.this, reply, Toast.LENGTH_SHORT).show();
   }

   

   
   
   // TODO
   protected void onActivityResult(int requestCode, int resultCode, Intent data) {

           if (resultCode == RESULT_OK) {
               // A contact was picked.  Here we will just display it
               // to the user.
        	   Toast.makeText(AndroBuntu.this, "Nice weather, today.", Toast.LENGTH_SHORT).show();
           }
   }

}



