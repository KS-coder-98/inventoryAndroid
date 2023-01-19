package pl.krzysiek.inventory.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import pl.krzysiek.inventory.model.ItemDto;

public class ItemArrayAdapter extends ArrayAdapter<ItemDto> {
    public ItemArrayAdapter(Context context, List<ItemDto> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemDto item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }
        TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
        textView.setText(item.getName());
        return convertView;
    }
}
