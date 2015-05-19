package gva.ydh.com.frescodemos;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {


    private ListView mlv_graphic;
    private List<String> contentList;
    private GraphicDetailAdapter gdAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_special_craphic_detail);
        Fresco.getImagePipelineFactory().getMainDiskStorageCache().clearAll();
        contentList = new ArrayList<>();
        contentList.add("http://www.sinaimg.cn/dy/slidenews/4_img/2015_12/704_1579346_230297.jpg");
        contentList.add("http://img.v89.com/group1/M01/05/CF/rBAA11SJGj6ANUmYAAHYXsbUhAM483_640*640.jpg");
        contentList.add("http://img.v89.com/group1/M07/05/CF/rBAA11SJGjuAYGYwAAHthrrvYrQ279_640*640.jpg");
        contentList.add("http://img.v89.com/group1/M05/05/CE/rBAA11SIRc2AR95TAAFos2CSxNk102_640*640.jpg");
        contentList.add("http://img.v89.com/group1/M05/05/CE/rBAA11SIRcaAC81hAAHeRIw0YkE550_640*640.jpg");
        contentList.add("http://img.v89.com/group1/M05/05/CF/rBAA11SJCT2AaVvHAAFPnJV76ic597_640*640.jpg");
        contentList.add("http://img.v89.com/group1/M01/05/CF/rBAA11SJCTiAPJeLAAFPo48Ykuk249_640*640.jpg");
        contentList.add("http://img.v89.com/group1/M05/06/C9/rBAA11UhWACAcl-pAAG5ICOGh7I506_640*640.jpg");
        contentList.add("http://img.v89.com/group1/M08/05/CF/rBAA11SJCTaAXAPXAAHyIyeKNgo933_640*640.jpg");
        contentList.add("http://img.v89.com/group1/M08/04/22/rBAA11OtUeqAILyjAAC2I8VNHUU462_640*640.jpg");
        contentList.add("http://img.v89.com/group1/M07/05/CF/rBAA11SJGjuAYGYwAAHthrrvYrQ279_640*640.jpg");
        contentList.add("http://img.v89.com/group1/M00/05/CF/rBAA11SJAGaAM-akAAF3aRTBmFY636_640*640.jpg");
        contentList.add("http://img.v89.com/group1/M04/06/E5/rBAA11UvlpWAWnJ8AAGmMlW-OZA506_640*640.jpg");
        contentList.add("http://img.v89.com/group1/M08/06/E5/rBAA11UvltKASRFrAAEP4CDN1do892_640*640.jpg");
        mlv_graphic = (ListView) findViewById(R.id.mlv_graphic_sss);
        gdAdapter = new GraphicDetailAdapter(MainActivity.this, contentList);
        mlv_graphic.setAdapter(gdAdapter);
    }


    public class GraphicDetailAdapter extends BaseAdapter {

        private Context context;
        private List<String> arrayList;
        private int screenWeight = 0;

        public GraphicDetailAdapter(Context context, List<String> arrayList) {
            this.context = context;
            this.arrayList = arrayList;
            this.screenWeight = getScreenWidth(context);
        }

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int arg0) {
            return arg0;
        }

        @Override
        public long getItemId(int arg0) {
            return arg0;
        }

        @Override
        public View getView(int arg0, View convertView, ViewGroup arg2) {

            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.graphic_detail_itemss, arg2, false);
                viewHolder = new ViewHolder();
                viewHolder.tv_text = (TextView) convertView.findViewById(R.id.tv_text);
                viewHolder.iv_graphic = (SimpleDraweeView) convertView.findViewById(R.id.iv_graphic);
                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tv_text.setText(arrayList.get(arg0));
            viewHolder.iv_graphic.setLayoutParams(new LinearLayout.LayoutParams(screenWeight, screenWeight));
            viewHolder.iv_graphic.setImageURI(Uri.parse(arrayList.get(arg0)));
            return convertView;
        }

        private class ViewHolder {
            public TextView tv_text;
            public SimpleDraweeView iv_graphic;
        }

        public DisplayMetrics getDisplayMetrics(Context context) {
            return context.getResources().getDisplayMetrics();
        }

        public int getScreenWidth(Context context) {
            DisplayMetrics metrics = getDisplayMetrics(context);
            return metrics.widthPixels;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Fresco.shutDown();
    }
}
