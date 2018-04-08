package app.my.drivingtest.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.socks.library.KLog;

import java.util.List;

import app.my.drivingtest.R;

public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private List<JageDialogSelctedBean> selectChoice;


    public GridViewAdapter(Context context, List<JageDialogSelctedBean> selectChoice) {
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
        viewHolder.iv_my_menu.setSelected(selectChoice.get(position).isSelected());


        return convertView;
    }

    class ViewHolder {
        protected ImageView iv_my_menu;

        public ViewHolder(View convertView) {
            iv_my_menu = (ImageView) convertView.findViewById(R.id.iv_my_menu);

        }
    }
}