package com.danielme.tipsandroid.listviewheaders;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.danielme.tipsandroid.listviewheaders.model.Content;
import com.danielme.tipsandroid.listviewheaders.model.Header;
import com.danieme.tipsandroid.listviewheaders.R;

/**
 * Custom adapter with "View Holder Pattern".
 * 
 * @author danielme.com
 * 
 */
public class CustomArrayAdapter extends ArrayAdapter<Object>
{

	private LayoutInflater layoutInflater;
	

	public CustomArrayAdapter(Context context, List<Object> objects)
	{
		super(context, 0, objects);
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		//header
		if (getItem(position) instanceof Header)
		{
			//check if this view exists or contains a content
			if (convertView == null || convertView.findViewById(R.id.textViewHeader) == null)
			{
				convertView = layoutInflater.inflate(R.layout.listview_header, null);
			}
			TextView textView = (TextView) convertView.findViewById(R.id.textViewHeader);
			Header header = (Header) getItem(position);
			textView.setText(header.getTitle());
		}
		else //ViewHolder Pattern for content
		{		
			Holder holder = null;
			//check if this view exists or contains a header
			if (convertView == null || convertView.findViewById(R.id.textViewHeader) != null)
			{
				holder = new Holder();
	
				convertView = layoutInflater.inflate(R.layout.listview_content, null);
				holder.setTextView1((TextView) convertView.findViewById(R.id.textView1));
				holder.setTextView2((TextView) convertView.findViewById(R.id.textView2));
				convertView.setTag(holder);
			}
			else
			{
				holder = (Holder) convertView.getTag();
			}
			Content content = (Content) getItem(position);
			holder.getTextView1().setText(content.getText1());
			holder.getTextView2().setText(content.getText2());
		}
		return convertView;
	}

}

class Holder
{

	private TextView textView1;

	private TextView textView2;

	public TextView getTextView1()
	{
		return textView1;
	}

	public void setTextView1(TextView textView)
	{
		this.textView1 = textView;
	}

	public TextView getTextView2()
	{
		return textView2;
	}

	public void setTextView2(TextView textView)
	{
		this.textView2 = textView;
	}

}