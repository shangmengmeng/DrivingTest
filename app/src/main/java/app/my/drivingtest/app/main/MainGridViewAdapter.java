package app.my.drivingtest.app.main;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

import app.my.drivingtest.R;
import app.my.drivingtest.view.JageDialogSelctedBean;

public class MainGridViewAdapter extends BaseAdapter {
    private Context context;
    private List<String> selectChoice;


    public MainGridViewAdapter(Context context, List<String> selectChoice) {
        this.context = context;
        this.selectChoice = selectChoice;

    }

    @Override
    public int getCount() {
        return selectChoice.size();
    }

    @Override
    public Object getItem(int position) {
        return selectChoice.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_my_menu_dialog, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        return convertView;
    }

    class ViewHolder {
        protected ImageView iv_my_menu;

        public ViewHolder(View convertView) {
            iv_my_menu = (ImageView) convertView.findViewById(R.id.iv_my_menu);

        }
    }
}