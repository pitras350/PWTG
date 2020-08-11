package pjblake.pwtg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends ArrayAdapter<Attacks>{

    private Context mContext;
    private List<Attacks> attackList = new ArrayList<>();

    public ListAdapter(Context context, ArrayList<Attacks> list) {
        super(context, 0 , list);
        mContext = context;
        attackList = list;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        Attacks currentAttack = attackList.get(position);

        TextView content = (TextView) listItem.findViewById(R.id.textView_name);
        content.setText(currentAttack.getContent());


        return listItem;
    }
}
