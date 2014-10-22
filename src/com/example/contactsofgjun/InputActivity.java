package com.example.contactsofgjun;

import com.example.contactsofgjun.data.*;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputActivity extends Activity {

	Button bt1,bt2;
	EditText et1,et2,et3;
	Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input);
		context = this;
		bt1 = (Button) findViewById(R.id.button1);
		bt2 = (Button) findViewById(R.id.button2);
		
		et1 = (EditText) findViewById(R.id.editText1);
		et2 = (EditText) findViewById(R.id.editText2);
		et3 = (EditText) findViewById(R.id.editText3);
		
		bt1.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				phoneDAO dao = new phoneDAODBImpl(context);
				dao.add(new Phone(0,et1.getText().toString(),et2.getText().toString(),et3.getText().toString()));

				finish();
			}});
	}
}
