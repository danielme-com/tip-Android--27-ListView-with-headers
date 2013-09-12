package com.danielme.tipsandroid.listviewheaders;

import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
import com.danielme.tipsandroid.listviewheaders.model.Content;
import com.danielme.tipsandroid.listviewheaders.model.Header;
import com.danieme.tipsandroid.listviewheaders.R;

/**
 * 
 * @author danielme.com
 * 
 */
public class MainActivity extends ListActivity
{

	private List<Object> dataset = getContent();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setListAdapter(new CustomArrayAdapter(this, dataset));

		getListView().setOnItemClickListener(new OnItemClickListener() {

			@SuppressLint("NewApi")
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Object item = dataset.get(position);
				if (item instanceof Header)
				{
					Toast.makeText(MainActivity.this, ((Header) item).getTitle(), Toast.LENGTH_SHORT).show();
					// back to header, see
					// http://danielme.com/tip-android-17-listview-back-to-top-volver-al-inicio/
					if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB)
					{
						getListView().setSelection(position);
					}
					else
					{
						getListView().smoothScrollToPositionFromTop(position, 0, 300);
					}
				}
				else
				{
					Toast.makeText(MainActivity.this, ((Content) item).getText1(), Toast.LENGTH_SHORT).show();
				}

			}
		});
	}

	private List<Object> getContent()
	{
		List<Object> list = new ArrayList<Object>(60);
		Content content = null;
		Header header = null;
		int j = 1;

		for (int i = 0; i < 50; i++)
		{
			// set a new header or section every five rows
			if (i % 5 == 0)
			{
				header = new Header();
				header.setTitle("Header number " + j);
				j++;
				list.add(header);
			}

			content = new Content();
			content.setText1("Text 1-" + (i + 1));
			content.setText2("Text 2-" + (i + 1));
			list.add(content);
		}

		return list;

	}
}