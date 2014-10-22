package com.example.contactsofgjun;

import java.util.ArrayList;

import com.example.contactsofgjun.data.*;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends Activity {


	ArrayList<Boolean> chkflag = new ArrayList<Boolean>();
	 Phone[] data;
	 ListAdapter adapter;
	 Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); 
        //MyTest();
        
    }
    protected void onResume()
    {
    	super.onResume();
        phoneDAO dao = new phoneDAODBImpl(this);
        data = dao.getAll();
        for (int i=0;i<data.length;i++) {
        	chkflag.add(false);
        }
        ListView lv = (ListView) findViewById(R.id.listView1);
        ListAdapter adapter = new ListAdapter(this, data);
        lv.setAdapter(adapter);
        
        lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				int ID = data[position].ID;
				Intent intent = new Intent(MainActivity.this,DetailActivity.class);
				intent.putExtra("ID", ID);
				startActivity(intent);
			}});
    }

    /*void MyTest() {
    	phoneDAO dao = new phoneDAODBImpl(this);
    	dao.add(new Phone(0, "Mike", "12345", "aaaa"));
    	dao.add(new Phone(0, "David","23456","bbbb"));
    	dao.add(new Phone(0, "Joy", "12345","cccc"));
    	Phone[] data = dao.getAll();
    	for(int i=0;i<data.length;i++) {
    		Log.d("DB", data[i].Name + "," + data[i].Tel);
    	}
    	dao.removeAll();
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
        if (id == R.id.action_add) {
        	Intent intent = new Intent(MainActivity.this, InputActivity.class);
        	startActivity(intent);
        }
        if (id == R.id.action_search) {
        	
        }
        if (id == R.id.action_delete) {
        	/*for(int i=chkflag.size()-1; i >=0; i--) {
        		if(chkflag.get(i)) {
//        			data.remove(i);
        			phoneDAO dao = new phoneDAODBImpl(context);
        			String datas = data[i].ID + "";
//        			Toast.makeText(MainActivity.this, datas, Toast.LENGTH_SHORT).show();
        			Log.d("mike", datas);
        			
        			phoneDBHelper helper = new phoneDBHelper(context);
        			SQLiteDatabase db = helper.getWritableDatabase();
//        			SQLiteDatabase dbr = helper.getReadableDatabase();
        			db.execSQL("delete from contacts where ID=" + data[i].ID);
        			dao.getAll();
        			db.close();
        			
        			
//        			chkflag.remove(i);
        		}
        	}
//        	adapter.notifyDataSetChanged();*/
        	AlertDialog.Builder alert = new AlertDialog.Builder(context);
        	alert.setTitle("Delete confirm");
        	alert.setPositiveButton("Ok", new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					phoneDAO dao = new phoneDAODBImpl(context);
					for(int i=0;i<chkflag.size();i++) {
						if(chkflag.get(i)) {
							dao.delete(data[i].ID);
						}
					}
					data = dao.getAll();
					chkflag.clear();
					for(int i=0;i<data.length;i++) {
						chkflag.add(false);
					}
					adapter.data =data; // data是MainActivity的，所以要指到adapter data
					adapter.notifyDataSetChanged();
				}});
				
        	alert.setNegativeButton("Cancel", null);
        	alert.show();
        }
 
        return super.onOptionsItemSelected(item);
    }
    
    class ListAdapter extends BaseAdapter
    {
    	Phone [] data;
    	Activity act;
    	private LayoutInflater inflater = null;
    	public ListAdapter(Activity a, Phone [] data)
    	{
    		this.act = a;
    		this.data = data;
    		inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	}
    	
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return data.length;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@SuppressLint({ "ViewHolder", "InflateParams" })
		@Override
		public View getView(final int position, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			View v = inflater.inflate(R.layout.listitem, null);
			TextView tv = (TextView)v.findViewById(R.id.tvName);
			tv.setText(data[position].Name);
			TextView tv2 = (TextView)v.findViewById(R.id.tvTel);
			tv2.setText(data[position].Tel);
			
			CheckBox cb = (CheckBox)v.findViewById(R.id.checkBox1);
			cb.setChecked(chkflag.get(position));
			
			cb.setOnCheckedChangeListener(new OnCheckedChangeListener(){

				@Override
				public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
					// TODO Auto-generated method stub
					chkflag.set(position, isChecked);
				}});
			
			return v;
		}
    }
}
