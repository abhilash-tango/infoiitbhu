package com.iit.infoiitbhu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    int c;
    Button add, sub, savebt;
    EditText user,pas;
    private MyDbClass dbase=new MyDbClass(this);
    
    TextView disp;
    
    String UserName="";
    String Password="";
    
    
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        c=0;
        add =(Button) findViewById(R.id.bt1);
        sub =(Button) findViewById(R.id.bt2);
        disp = (TextView) findViewById(R.id.tv);
        user=(EditText) findViewById(R.id.edT1);
        pas=(EditText) findViewById(R.id.edT2);
        savebt=(Button) findViewById(R.id.btsave);
        
        add.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				c+=1;
				disp.setText("Your total is" +c);
			}
		});
        sub.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				c -=1;
				disp.setText("Your total is" +c);
			}
		});
       savebt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				UserName=user.getText().toString();
				Password=pas.getText().toString();
				
			       dbase.open();
			       dbase.createEntry(UserName, Password);
			       //disp.setText(dbase.viewData());
			       dbase.close();
			}
		});
       
        
    }


   
    
}
