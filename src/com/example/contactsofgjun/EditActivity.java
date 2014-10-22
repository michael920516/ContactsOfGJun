package com.example.contactsofgjun;

import com.example.contactsofgjun.data.Phone;
import com.example.contactsofgjun.data.phoneDAO;
import com.example.contactsofgjun.data.phoneDAODBImpl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditActivity extends Activity {

	EditText et1,et2,et3;
	Button bt1,bt2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		//
		Intent intent = getIntent();
		int ID = intent.getIntExtra("ID", 0);
		phoneDAO dao = new phoneDAODBImpl(this);
		Phone p = dao.getPhone(ID);
		
		et1 = (TextView)findViewById(R.id.textView1);
		tv2 = (TextView)findViewById(R.id.textView2);
		tv3 = (TextView)findViewById(R.id.textView3);
		tv1.setText(p.Name);
		tv2.setText(p.Tel);
		tv3.setText(p.Addr);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
