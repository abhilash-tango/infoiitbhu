package com.iit.infoiitbhu;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Start extends Activity {
 Button enter;
 EditText l1,l2;
 int x=0;
 final Context context= this;
 private MyDbClass dbase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        dbase = new MyDbClass(this);
        dbase.open();
        dbase.createEntry("1", "2");
        
        
        
        enter = (Button)findViewById(R.id.button1);
        l1 = (EditText)findViewById(R.id.editText1);
        l2 = (EditText)findViewById(R.id.editText2);
        enter.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//String str1="Check this";
				//l1.setText(dbase.getData("1", "2"));
				if (dbase.getData(l1.getText().toString(), l2.getText().toString())){
					
					x=0;
					Intent ourIntent = new Intent(com.iit.infoiitbhu.Start.this,com.iit.infoiitbhu.MainActivity.class );
					startActivity(ourIntent);
					
					
					
				}
				else{
					
					x++;
					if(x>3)
					{
						System.exit(0);
					}
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							context);
			 
						// set title
						alertDialogBuilder.setTitle("Wrong Password");
			 
						// set dialog message
						alertDialogBuilder
							.setMessage("Chutiya na bana! \n Press no to exit")
							.setCancelable(true)
							.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,int id) {
									// if this button is clicked, close
									// current activity
									
								}
							  })
							.setNegativeButton("No",new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,int id) {
									// if this button is clicked, just close
									// the dialog box and do nothing
									System.exit(0);
									dialog.cancel();
								}
							});
			 
							// create alert dialog
							AlertDialog alertDialog = alertDialogBuilder.create();
			 
							// show it
							alertDialog.show();
						}
				}
			}
		 );
   dbase.close();
    }
    


   /* @Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	*/



	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start, menu);
        return true;
    }
    
}
