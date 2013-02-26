package com.kpbird.chipsedittextlibrary;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

public class ChipsAdapter extends BaseAdapter implements Filterable {

	private ArrayList<ChipsItem> items;
	private ArrayList<ChipsItem> suggestions;
	private Context ctx;
	private LayoutInflater inflater;
	private String TAG = this.getClass().getSimpleName();

	public ChipsAdapter(Context context,ArrayList<ChipsItem> items) {
		super();
		this.ctx = context;
		this.items = items;
		this.suggestions = new ArrayList<ChipsItem>();
	}

	@Override
	public int getCount() {
		return suggestions.size();
	}

	@Override
	public ChipsItem getItem(int position) {
		return suggestions.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public int getImage(String title){
		Log.i(TAG, "Title " + title);
		int img = R.drawable.android;
		for(int i=0;i<items.size();i++){
			if(items.get(i).getTitle().trim().toLowerCase().startsWith(title.trim().toLowerCase())){
				img = items.get(i).getImageid();
				Log.i(TAG, "Found " + title);
				break;
			}
		}
		
		return img;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh;
		View view = convertView;
		if (view == null) {
			if (inflater == null)
				inflater = LayoutInflater.from(ctx);
			view = inflater.inflate(R.layout.chips_adapter, null);
			vh = new ViewHolder();
			vh.img = (ImageView) view.findViewById(R.id.imageView1);
			vh.tv = (TextView) view.findViewById(R.id.textView1);

			view.setTag(vh);
		} else {
			vh = (ViewHolder) view.getTag();
		}

		Log.i(TAG, suggestions.get(position).getTitle() +  " = " + suggestions.get(position).getImageid());
		vh.img.setImageResource(suggestions.get(position).getImageid());
		vh.tv.setText(suggestions.get(position).getTitle());

		return view;
	}

	class ViewHolder {
		TextView tv;
		ImageView img;
	}

	@Override
	public Filter getFilter() {
		return nameFilter;
	}

	Filter nameFilter = new Filter() {

		@Override
		public CharSequence convertResultToString(Object resultValue) {
//			Log.i(TAG, "convertResultToString :" + resultValue);
			String str = ((ChipsItem) resultValue).getTitle();
			return str;
		}

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			Log.d("filter", "" + constraint);
			FilterResults filterResults = new FilterResults();
			if (constraint != null) {

				suggestions.clear();
				try {
					for (int i = 0; i < items.size(); i++) {

						if (items.get(i).getTitle().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
//							Log.d("filter", "Found --- " + items.get(i).getTitle());
							suggestions.add(items.get(i));
						}

					}
				} catch (Exception e) {
				}
				filterResults.values = suggestions;
				filterResults.count = suggestions.size();
			}
			return filterResults;
		}

		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {
//			Log.i(TAG, "publish results " + results.count);

			if (results != null && results.count > 0) {
				notifyDataSetChanged();
			} else {
				notifyDataSetInvalidated();
			}

		}
	};

}
