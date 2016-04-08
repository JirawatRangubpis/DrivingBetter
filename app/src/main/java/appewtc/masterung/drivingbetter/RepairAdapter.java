package appewtc.masterung.drivingbetter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by apple on 1/22/16 AD.
 */
public class RepairAdapter extends BaseAdapter{

    // Explicit

    private Context objContext;
    private  String[] titlStrings, imageStrings;

    public RepairAdapter(Context objContext, String[] titlStrings, String[] imageStrings) {
        this.objContext = objContext;
        this.titlStrings = titlStrings;
        this.imageStrings = imageStrings;
    }   // Constructor

    @Override
    public int getCount() {
        return imageStrings.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater objLayoutInflater = (LayoutInflater) objContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View objView1 = objLayoutInflater.inflate(R.layout.repair_listview, viewGroup, false);

        // Icon
        ImageView iconImageView = (ImageView) objView1.findViewById(R.id.imageView6);
        Picasso.with(objContext)
                .load(imageStrings[i])
                .resize(100, 100)
                .into(iconImageView);

        // Title
        TextView titleTextView = (TextView) objView1.findViewById(R.id.textView40);
        titleTextView.setText(titlStrings[i]);

        return objView1;
    }
}   // Main Class
